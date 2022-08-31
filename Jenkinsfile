pipeline {
    agent any
    environment {
        // Setup Ruby to PATH
        RUBY_HOME = "/usr/local/opt/ruby"
        PATH = "$RUBY_HOME/bin:$PATH"
        LANG = "en_US.UTF-8"
    }
    parameters {
        string(name: 'APP_BUILD_FOLDER', defaultValue: 'app/build', description: 'Application build output folder')
    }
    options {
        // Stop the build early in case of compile or test failures
        skipStagesAfterUnstable()
    }
    stages{
        stage('Setup') {
            steps {
                script {
                    sh 'gem install bundler'
                    sh 'bundle install'
                    // Copy node env file to export environment variables
                    withCredentials([file(credentialsId: 'env-default', variable: 'env')]) {
                        sh 'rm -f .env.default'
                        sh 'cp $env .env.default'
                    }
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    sh 'bundle exec fastlane build_dev'
                }
            }
        }
        stage('Parallel Stage') {
            failFast true
            parallel {
                stage('Test') {
                    steps {
                        script {
                            sh 'bundle exec fastlane unit_test'
                        }
                    }
                }
                stage('Check') {
                    steps {
                        script {
                            sh 'bundle exec fastlane check'
                        }
                    }
                }
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
            steps {
                script {
                    sh 'echo deploy'
                }
            }
        }
        stage('Production') {
            steps {
                script {
                    // Copy release keystore to workspace
                    withCredentials([file(credentialsId: 'keystore-release', variable: 'keystore')]) {
                        sh 'rm -rf config/keystore'
                        sh 'mkdir -p config/keystore'
                        sh 'cp $keystore config/keystore/'
                    }
                    sh 'bundle exec fastlane build_prod'
                }
            }
        }
    }
    post {
        success {
            dir("${params.APP_BUILD_FOLDER}") {
                echo 'Archiving APKs...'
                archiveArtifacts artifacts: "**/*.apk"
                echo 'Archiving Mappings...'
                archiveArtifacts artifacts: "**/mapping.txt", allowEmptyArchive: true
                echo 'Archiving Reporters...'
                archiveArtifacts artifacts: 'reports/**'
            }
        }
        cleanup {
            cleanWs()
        }
    }
}


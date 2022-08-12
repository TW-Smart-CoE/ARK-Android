pipeline {
    agent any
    environment {
        // Setup Ruby to PATH
        RUBY_HOME = "/usr/local/opt/ruby"
        PATH = "$RUBY_HOME/bin:$PATH"
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
        stage('Test') {
            steps {
                script {
                    sh 'bundle exec fastlane check'
                    sh 'bundle exec fastlane unit_test'
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
    }
    post {
        always {
            echo 'Archiving APKs...'
            archiveArtifacts artifacts: "${params.APP_BUILD_FOLDER}/**/*.apk"
            echo 'Archiving Mappings...'
            archiveArtifacts artifacts: "${params.APP_BUILD_FOLDER}/**/mapping.txt", allowEmptyArchive: true
            echo 'Archiving Reporters...'
            archiveArtifacts artifacts: '${params.APP_BUILD_FOLDER}/reports/**/*.*'
        }
    }
}


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
        choice(name: 'APP_BUILD_ENV', choices: ['dev', 'uat', 'staging', 'prod'], description: 'Pipeline build env: dev/uat/staging/prod, default is dev')
        booleanParam(name: 'ENABLE_PUBLISH', defaultValue: true, description: 'Enable publish to maven')
        string(name: 'PUBLISH_VERSION', defaultValue: '1.0.0', description: 'Set publish version')
        booleanParam(name: 'IS_SNAPSHOT', defaultValue: true, description: 'Set whether snapshot')
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
        stage('Build Dev') {
            when { expression { params.APP_BUILD_ENV == 'dev'} }
            steps {
                script {
                    sh 'bundle exec fastlane build_dev'
                }
            }
        }
        stage('Build Uat') {
            when { expression { params.APP_BUILD_ENV == 'uat'} }
            steps {
                initKeyStore()
                script {
                    sh 'bundle exec fastlane build_uat'
                }
            }
        }
        stage('Build Staging') {
            when { expression { params.APP_BUILD_ENV == 'staging'} }
            steps {
                initKeyStore()
                script {
                    sh 'bundle exec fastlane build_staging'
                }
            }
        }
        stage('Build Prod') {
            when { expression { params.APP_BUILD_ENV == 'prod'} }
            steps {
                script {
                    initKeyStore()
                    sh 'bundle exec fastlane build_prod'
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
        stage('Publish') {
            when {
                allOf {
                    expression { params.ENABLE_PUBLISH == true }
                }
            }
            steps {
                script {
                    def version = params.PUBLISH_VERSION
                    if (params.IS_SNAPSHOT == true) {
                        version += "-SNAPSHOT"
                    }
                    sh "bundle exec fastlane publish_with_env buildEnv:${params.APP_BUILD_ENV} publishVersion:${version}"
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

// Copy release keystore to workspace
def initKeyStore() {
    withCredentials([file(credentialsId: 'keystore-release', variable: 'keystore')]) {
        sh 'rm -rf config/keystore'
        sh 'mkdir -p config/keystore'
        sh 'cp $keystore config/keystore/'
    }
}

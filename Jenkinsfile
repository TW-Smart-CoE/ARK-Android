pipeline {
    agent any
    environment {
        // Setup Ruby to PATH
        PATH = "/usr/local/Cellar/ruby/3.1.2/bin:$PATH"
        SLACK_URL = credentials('ark-slack-webhook-url')
    }
    options {
        // Stop the build early in case of compile or test failures
        skipStagesAfterUnstable()
    }
    stages{
        stage('Setup') {
            steps {
                script {
                    sh ("gem install bundler")
                    sh ('bundle install')
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    sh ('bundle exec fastlane build_dev')
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh ('bundle exec fastlane check')
                    sh ('bundle exec fastlane unit_test')
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
                    sh ('echo deploy')
                }
            }
        }
    }
}


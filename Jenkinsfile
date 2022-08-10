pipeline {
    agent any
    stages{
        stage('Build') {
            steps {
                script {
                    sh ('echo build')
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh ('echo unit test')
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


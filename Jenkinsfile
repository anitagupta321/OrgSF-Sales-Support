@Library('CICD_pipeline-central-java-lib') _

pipeline {

    agent {
        label 'java'
    }

    tools {
        jdk 'OpenJDK 13'
    }

    stages {
        stage('Clean') {
            steps {
                standardGradleClean()
            }
        }

        stage('Assemble') {
            steps {
                standardGradleAssemble()
            }
        }

        stage('Check') {
            steps {
                standardGradleCheck()
            }
        }

        stage('Build') {
            steps {
               standardGradleBuild()
            }
        }

// TODO: Uncomment the Publish stage when you use this template
//         stage('Publish') {
//             when {
//                 expression { env.BRANCH_NAME == 'main' }
//             }
//             steps {
//                 standardGradleMavenPublish()
//             }
//         }
    }

    post {
        always {
            standardArchiving()
        }
    }
}

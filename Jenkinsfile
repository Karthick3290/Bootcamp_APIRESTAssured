// pipeline {
//    agent any
//
//    stages {
//       stage('checkout') {
//          steps {
//             checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Karthick3290/Bootcamp_APIRESTAssured']]])
//          }
//       }
//       stage('Maven installation') {
//          steps {
//              sh 'mvn clean test'
//         }
//       }
//    }
// }

pipeline {
    agent any
    stages {
        stage('Unit test') {
            steps {
                echo 'Running unit tests'
                echo 'Running Contract tests'
            }
        }
        stage('Build') {
            steps {
                echo 'building...'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'yooo deploying'
                }
            }
        }
        stage('API test') {
            environment {
                JUICE_SHOP_URL= "https://juice-shop.herokuapp.com/"
            }
            steps {
                echo 'Running API tests in'
                dir('api-tests') {
                    git url: 'https://github.com/Karthick3290/Bootcamp_APIRESTAssured'
                }
                sh 'cd api-tests && bash run-api-tests.sh'
                }
                post{
                sh 'cd api-tests && bash mvn-cleanup.sh'
                }

        }
//         stage('UI test') {
//             steps {
//                 echo 'Running UI tests in'
//                 dir('ui-tests') {
//                     git url: 'https://github.com/chanilharisankar/bootcamp-automation.git'
//                 }
//                 sh 'bash ui-tests/run-ui-test.sh'
//                 }
//         }
    }
}

pipeline {
    agent any

    tools {
        maven 'Maven 3.9.16'
    }

    environment {
        IMAGE_NAME = "akashchemkure97/java-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=java-app
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build \
                -t $IMAGE_NAME:$IMAGE_TAG \
                -t $IMAGE_NAME:latest .
                '''
            }
        }

        stage('Trivy Scan') {
            steps {
                sh '''
                trivy image --exit-code 0 \
                --severity HIGH,CRITICAL \
                $IMAGE_NAME:latest
                '''
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                    docker push $IMAGE_NAME:$IMAGE_TAG
                    docker push $IMAGE_NAME:latest

                    docker logout
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                export KUBECONFIG=/var/jenkins_home/.kube/config

                kubectl apply -f k8s/

                kubectl rollout restart deployment/java-app

                kubectl rollout status deployment/java-app
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                sh '''
                export KUBECONFIG=/var/jenkins_home/.kube/config

                kubectl get pods

                kubectl get svc
                '''
            }
        }
    }

    post {

        success {
            echo 'Pipeline executed successfully.'
        }

        failure {
            echo 'Pipeline failed.'
        }

        always {
            cleanWs()
        }
    }
}

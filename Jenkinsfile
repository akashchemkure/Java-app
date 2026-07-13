pipeline {
    agent any

    tools {
        maven 'Maven 3.9.16'
    }

    environment {
        IMAGE_NAME = "java-app"
        IMAGE_TAG = "latest"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-pat',
                    url: 'https://github.com/akashchemkure/java-app.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
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
                    docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                '''
            }
        }
        
        stage('Deploy Container') {
           steps {
               sh '''
                   docker rm -f java-app-container || true

                   docker run -d \
                   --name java-app-container \
                   -p 8080:8080 \
                    java-app:latest
                '''
            }
        }
        stage('Show Docker Images') {
            steps {
                sh 'docker images'
            }
        }

    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}

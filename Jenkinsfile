pipeline {
    agent any

    tools {
        maven 'Maven 3.9.16'
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
                sh 'docker build -t java-app:latest .'
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

        stage('Verify Deployment') {
            steps {
                sh 'docker ps'
            }
        }
    }

    post {
        success {
            echo "Application deployed successfully!"
        }

        failure {
            echo "Pipeline failed."
        }
    }
}

# 🚀 Java Spring Boot CI/CD Pipeline using Jenkins, SonarQube & Docker

## 📌 Project Overview

This project demonstrates a complete CI/CD pipeline for a Java Spring Boot application using Jenkins.

Whenever code is pushed to GitHub, Jenkins automatically:

- Checks out the latest source code
- Builds the application using Maven
- Performs static code analysis using SonarQube
- Builds a Docker image
- Deploys the latest Docker container automatically

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Maven
- Jenkins
- SonarQube
- Docker
- GitHub
- GitHub Webhooks
- AWS EC2
- Ubuntu

---

## 🏗 CI/CD Pipeline

```text
Developer
      │
      ▼
GitHub Repository
      │
GitHub Webhook
      │
      ▼
Jenkins Pipeline
      │
      ├── Checkout Source
      ├── Maven Build
      ├── SonarQube Analysis
      ├── Docker Image Build
      ├── Deploy Docker Container
      └── Verify Deployment
```

---

## 📂 Project Structure

```
java-app/
│
├── src/
├── Dockerfile
├── Jenkinsfile
├── pom.xml
├── README.md
└── screenshots/
```

---

## 🚀 How to Run Locally

Clone the repository

```bash
git clone https://github.com/akashchemkure/java-app.git
cd java-app
```

Build the application

```bash
mvn clean package
```

Run locally

```bash
java -jar target/java-app-1.0.0.jar
```

---

## 🐳 Docker

Build image

```bash
docker build -t java-app .
```

Run container

```bash
docker run -d -p 8080:8080 --name java-app-container java-app
```

---

## 📸 Screenshots

- Jenkins Pipeline
- SonarQube Dashboard
- Docker Container
- Running Application

---

## 👨‍💻 Author

Akash Chemkure

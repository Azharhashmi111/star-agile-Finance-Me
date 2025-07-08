pipeline {
    agent any
    stages {
        stage('Clone Repo') {
            steps {
                echo "Cloning repo..."
                git 'https://github.com/Azharhashmi111/star-agile-Finance-Me.git'
            }
        }
        stage('Build with Maven') {
            steps {
                echo "Running mvn clean install"
                sh 'mvn clean install'
            }
        }
    }
}


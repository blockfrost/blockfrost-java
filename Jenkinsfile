pipeline {
    agent any

    tools {
        jdk 'jdk-8'
    }

    stages {

        stage('Build') {
            steps {
                 sh  './gradlew clean build'
            }
        }

        stage('Results') {
            steps {
                archiveArtifacts 'build/libs/*.jar'
            }
        }
    }
}

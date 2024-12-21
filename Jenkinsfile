pipeline {
      agent any
      environment {
        MAVEN_ARGS=" -e clean install"
        registry = ""
        dockerContainerName = 'vocab-service'
        dockerImageName = 'vocab-service'
      }
      stages {
         stage('Build') {
              environment {
                    VOCAB_DB_URL = "${env.VOCAB_DB_URL}"
                    POSTGRE_USERNAME = "${env.POSTGRE_USERNAME}"
                    POSTGRE_PASS = "${env.POSTGRE_PASS}"
              }
              steps {
                    withMaven(maven: 'MAVEN_ENV') {
                        sh "mvn ${MAVEN_ARGS}"
                    }
              }
         }
         stage('clean container') {
              steps {
                   sh 'docker ps -f name=${dockerContainerName} -q | xargs --no-run-if-empty docker container stop'
                   sh 'docker container ls -a -fname=${dockerContainerName} -q | xargs -r docker container rm'
                   sh 'docker images -q --filter=reference=${dockerImageName} | xargs --no-run-if-empty docker rmi -f'
              }
         }
         stage('build image') {
              steps {
                sh 'docker build -t ${dockerImageName} .'
              }
         }
         stage('docker-compose start') {
              steps {
                sh 'docker compose up -d --build'
              }
         }
      }
}
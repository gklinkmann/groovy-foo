#!groovy

node {

   stage('checkout') {
      checkout scm
   }

   stage('check tools') {
      sh "pwd"
      sh "gradle --version"
   }

   stage('clean') {
      sh "gradle clean"
   }

   stage('test') {
   	  try {
      	sh "gradle test"
   	  } catch (Exception e){
   	  	echo "test of ${env.BUILD_ID} failed on ${env.JENKINS_URL}"
   	  	throw e	
   	  } finally {
   	    junit 'build/test-results/**/*.xml'    
   	  }
   }
   
   stage('build') {
      sh "gradle jar"
   	  archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
   }
   
}
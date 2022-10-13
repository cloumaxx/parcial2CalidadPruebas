pipeline {
    agent any
    options {
	    skipStagesAfterUnstable()
    }
	tools {
		jdk 'openjdk-11'
	}
    stages {
		stage('Build') {
			steps {
				sh 'cd /home && mvn -B -DskipTests clean package'
	    	}
		}
		stage('Test') {
			steps {
				sh 'mvn test'
	    	}
			post {
				always {
					junit 'target/surefire-reports/*.xml'
				}
			}
		}
		stage('Deliver') {
			steps {
				sh 'mvn install'
			}
		}
    }
}

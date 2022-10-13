pipeline {
    agent {
		docker {
	    	image 'maven:3.8.6-openjdk-11'
	    	args '-v /root/.m2:/root/.m2 -v /usr/share:/usr/share -v /etc/maven:/etc/maven'
		}
    }
	options {
		skipStagesAfterUnstable()
	}
    stages {
		stage('Build') {
			steps {
				sh 'mvn -B -DskipTests clean package'
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
				sh 'home/jenkinsDocker/deliver.sh'
			}
		}
    }
}

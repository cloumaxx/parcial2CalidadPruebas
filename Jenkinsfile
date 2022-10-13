pipeline {
    agent any
    options {
	    skipStagesAfterUnstable()
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
				sh 'echo $PWD'
				sh 'mv target/*.war /home/outputJAR'
	    		}
			}
		}
		post {
			always {
				mail to: 'juancho1198@gmail.com',
				subject: 'Jenkins Pipeline -> Build Completed',
				body: 'Build Completed OK'
			}
		}	
}

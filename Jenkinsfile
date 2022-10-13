pipeline {
    agent any
    options {
	    skipStagesAfterUnstable()
		timeout(time: 15, unit: 'MINUTES')
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
				echo 'Test, Build and Deliver completed'
			}
			success {
				mail to: 'juancho1198@gmail.com',
				subject: 'Jenkins Pipeline - Success',
				body: 'Build Completed: Status -> OK'
			}
			unstable {
				mail to: 'juancho1198@gmail.com',
				subject: 'Jenkins Pipeline - Unstable',
				body: 'Build Completed: Status -> Unstable!'
			}
			failure {
				mail to: 'juancho1198@gmail.com',
				subject: 'Jenkins Pipeline - Failure',
				body: 'Build Completed: Status -> PAILANDERS PEPE'
			}
			changed {
				echo 'Build Completed: Status -> Changed'
				echo 'Pilas mano'
			}		
		}	
}

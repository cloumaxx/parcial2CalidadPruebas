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
				sh 'mv target/*.jar /home/outputJAR'
	    		}
			}
		}
}

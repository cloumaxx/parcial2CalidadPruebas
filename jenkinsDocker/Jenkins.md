#### Pasos pa una app java con jenkins y maven con docker
---

```sh

docker pull jenkins:jenkins

docker network create jenkins
# necesitamos acceso a la red desde 
# afuera del contenedor de jenkins
# (poder localizarlo con desde otro)


Nos tocará crear una Dockerfile para mover la imagen de jenkins acorde al contenedor desde el que lo vamos a usar.


```Dockerfile
FROM jenkins/jenkins
USER root
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
 https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
 signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
 https://download.docker.com/linux/debian \
 $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli
USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean docker-workflow"


```
Con blueocean para la pipeline de CD suavecito

Ahora creamos la imagen nueva.

`docker build -t jenkinsblue`

El siguiente paso es crítico, asegurarse de el directorio actual en la terminal sea el principal del repositorio. Necesitamos que se exponga el directorio actual del repositorio para que jenkins pueda saber cuando ocurre un cambio (un simple pull,push)


```bash

docker run --name jenkins-blueocean \
--detach \
--network jenkins \
--env DOCKER_HOST=tcp://docker:2376 \
--env DOCKER_CERT_PATH=/certs/client \
--env DOCKER_TLS_VERIFY=1 \
--publish 8080:8080 --publish 50000:50000 \
--volume jenkins-data:/var/jenkins_home \
--volume jenkins-docker-certs:/certs/client:ro \
--volume "$PWD":/home \
--restart=on-failure \
--env JAVA_OPTS="-Dhudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT=true" \
jenkinsblue

```

---
##### Jenkinsfile
La Jenkinsfile es la manera programatica de expresar la pipeline en terminos claros de pasos. Acá está la que usamos con maven:

```bash

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
```
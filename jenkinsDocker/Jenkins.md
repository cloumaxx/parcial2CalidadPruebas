#### Pasos pa una app java con jenkins y maven con docker
---

```sh

docker pull jenkins:jenkins

docker network create jenkins
# necesitamos acceso a la red desde 
# afuera del contenedor de jenkins
# (poder localizarlo con desde otro)

# necesitamos meter docker dentro de docker
# gracias a docker, podemos hacer eso

# Bajamos la imagen de docker
docker pull docker:dind


# Ahora le damos hello container 

docker run \
--name jenkins-docker \
--rm \
--detach \
--privileged \
--network jenkins \
--env DOCKER_TLS_CERTIDR=/certs \
--network-alias docker \
--volume jenkins_data:/var/jenkins_home \
--publish 2376:2376 \
--publish 3000:3000 --publish 5000:5000 \
docker:dind \
--storage-driver overlay2


```

Ya aqui tenemos nuestro contenedor donde vamos a meter jenkins, nos tocará crear una Dockerfile para mover la imagen de jenkins acorde al contenedor desde el que lo vamos a usar.

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

docker run --name jenkins-blueocean --detach \
--network jenkins --env DOCKER_HOST=tcp://docker:2376 \
--publish 8080:8080 --publish 50000:50000 \
--volume jenkins-data:/var/jenkins_home \
--volume jenkins-docker-certs:/certs/client:ro \
--volume "$PWD":/home \
--restart=on-failure \
--env JAVA_OPTS="-Dhudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT=true" \
jenkinsblue


```


La Jenkinsfile es la manera programatica de expresar la pipeline en terminos claros de pasos. Acá está una suave con maven:

```bash

pipeline {
    agent {
		docker {
	    	image 'maven:3.8.3-adoptopenjdk-11'
	    	args '-v /root/.m2:/root/.m2'
		}
    }
	optipms {
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
				sh './jenkinsDocker/deliver.sh'
			}
		}
    }
}
```
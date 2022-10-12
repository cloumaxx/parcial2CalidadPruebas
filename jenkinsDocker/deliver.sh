#!/usr/bin/env bash

#script del deploy:
# original de jenkins-docs:
# URL: https://github.com/jenkins-docs/simple-java-maven-app
# faltaria uno mas para copiar el war 
# ------------------------------------

echo 'Maven instalará la aplicación Java construida con Maven'
echo 'dentro del repo local de Maven, que estará en Jenkins (docker-data volume)'

set -x
mvn jar:jar install:install help:evaluate -Dexpression=project.name
set +x

echo 'El siguiente comando encuentra el nombre del projecto buscandolo en pom.xml'
set -x
NAME=$(mvn help:evaluate -Dexpression=project.name | grep "^[^\[]")
set +x

echo 'Lo mismo pero con la version'
set -x
VERSION=$(mvn help:evaluate -Dexpression=project.version | grep "^[^\[]")
set +x

echo 'Aqui se ejecuta la aplicación Java (que Jenkins construyó con Maven)'
echo 'y se muestra en la UI de Jenkins'
set -x
java -jar target/"${NAME}"-"${VERSION}".jar

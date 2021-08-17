FROM adoptopenjdk/openjdk11:latest

MAINTAINER "Jonathan Augusto"

COPY target/quasar-0.0.1-SNAPSHOT.jar meli.jar

ENTRYPOINT ["java","-jar","/meli.jar"]
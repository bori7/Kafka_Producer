FROM openjdk:11-jre-slim

VOLUME /usr
WORKDIR /usr/local/lib
COPY target/*.jar /usr/local/lib/producer-spring-kafka-boot-0-SNAPSHOT.jar


EXPOSE 8082

ENV RUN_PROFILE dev
ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","/usr/local/lib/producer-spring-kafka-boot-0-SNAPSHOT.jar"]
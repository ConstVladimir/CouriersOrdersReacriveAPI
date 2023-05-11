FROM gradle:7.6.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test -x checkstyleTest --no-daemon

FROM openjdk:17.0.1-jdk-slim

ARG JAR_FILE=build/libs/*.jar

WORKDIR /opt/app

COPY ${JAR_FILE} yandex-lavka.jar

ENTRYPOINT ["java","-jar","yandex-lavka.jar"]

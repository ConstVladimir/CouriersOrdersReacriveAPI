FROM openjdk:17.0.1-jdk-slim AS build

COPY . /home/gradlew/src
WORKDIR /home/gradlew/src
RUN ./gradlew build

FROM openjdk:17.0.1-jdk-slim AS production

ARG JAR_FILE=/home/gradlew/src/build/libs/*SNAPSHOT.jar

WORKDIR /opt/app

COPY --from=build ${JAR_FILE} yandex-lavka.jar

ENTRYPOINT ["java","-jar","yandex-lavka.jar"]

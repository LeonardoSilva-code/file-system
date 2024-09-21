FROM maven:3.9.9-eclipse-temurin-22 AS build

COPY src /app/src

COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -U

FROM openjdk:22

WORKDIR /app

COPY --from=build /app/target/filesystem-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
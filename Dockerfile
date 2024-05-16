# Fase 1 construccion
FROM maven:3.9.5 AS build
LABEL authors="btorregrosa"
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Fase 2
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar
CMD ["/bin/sh", "-c", "java -jar app.jar"]

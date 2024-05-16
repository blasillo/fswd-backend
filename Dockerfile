FROM amazoncorretto:17

# Copia el archivo JAR de la aplicación Spring Boot a la imagen
COPY target/*.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
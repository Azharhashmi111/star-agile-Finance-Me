# Use a lightweight Java 8 base image
FROM openjdk:8-jdk-alpine

# Copy the built JAR file into the image
COPY target/*.jar /app.jar

# ✅ Copy application.properties into container
COPY src/main/resources/application.properties /application.properties

# ✅ Run app and point to external config file explicitly
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=file:/application.properties"]

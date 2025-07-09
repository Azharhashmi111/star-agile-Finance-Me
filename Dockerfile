FROM openjdk:11

# Copy JAR file
COPY target/*.jar /app.jar

# ✅ Copy external config
COPY src/main/resources/application.properties /application.properties

# ✅ Use this entrypoint so Spring Boot uses correct config file
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=file:/application.properties"]

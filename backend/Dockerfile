# Use Java 21 lightweight base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the built jar from host to container
COPY target/*.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8082

# Define environment variables for Spring Boot
ENV SPRING_PROFILES_ACTIVE=prod

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]

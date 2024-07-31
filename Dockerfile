# Use the Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory to /app
WORKDIR /app

# Copy the backend project directory contents to the working directory in the Docker image
COPY restaurant_backend/com.lididimi.restaurant /app

# Run Maven build
RUN mvn clean package -DskipTests

# Use a slimmer JDK image to run the application
FROM openjdk:17.0.1-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage to the runtime image
COPY --from=build /app/target/com.lididimi.restaurant-0.0.1-SNAPSHOT.jar /app/com.lididimi.restaurant.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/com.lididimi.restaurant.jar"]

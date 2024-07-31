# Use the Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/com.lididimi.restaurant-0.0.1-SNAPSHOT.jar com.lididimi.restaurant.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "com.lididimi.restaurant.jar"]

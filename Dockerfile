FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY COPY restaurant_backend/com.lididimi.restaurant /app/
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/com.lididimi.restaurant-0.0.1-SNAPSHOT.jar /app/com.lididimi.restaurant.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/com.lididimi.restaurant.jar"]

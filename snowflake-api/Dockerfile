# Build
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /src
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

# Runtime
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /src/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
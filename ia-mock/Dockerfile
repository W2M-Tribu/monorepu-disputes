FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/ia-mock-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3002
ENTRYPOINT ["java", "-jar", "/app.jar"]
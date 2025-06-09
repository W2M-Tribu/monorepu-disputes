FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/sap-mock-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3001
ENTRYPOINT ["java", "-jar", "/app.jar"]
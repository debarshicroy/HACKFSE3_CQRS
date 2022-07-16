FROM openjdk:11
EXPOSE 8081
COPY target/command-service.jar command-service.jar
ENTRYPOINT ["java", "-jar", "/command-service.jar"]
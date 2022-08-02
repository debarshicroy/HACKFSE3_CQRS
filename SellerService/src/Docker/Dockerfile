FROM openjdk:11
#EXPOSE 8082
#COPY target/query-service.jar query-service.jar
ADD maven/*.jar query-service.jar
ENTRYPOINT ["java", "-jar", "/query-service.jar"]
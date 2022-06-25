FROM openjdk:11
EXPOSE 8085
COPY ./target/transfer-service-0.0.1-SNAPSHOT.jar transfer-service.jar
ENTRYPOINT ["java","-jar","transfer-service.jar"]
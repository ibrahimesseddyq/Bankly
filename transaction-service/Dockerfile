FROM openjdk:11
WORKDIR app/
COPY target/transaction-service-0.0.1-SNAPSHOT.jar app/
EXPOSE 8090:8090
ENTRYPOINT ["java","-jar","app/transaction-service-0.0.1-SNAPSHOT.jar"]

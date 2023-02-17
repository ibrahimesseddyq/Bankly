FROM openjdk:11
WORKDIR app/
COPY target/wallet-service-0.0.1-SNAPSHOT.jar app/
EXPOSE 9003:9003
ENTRYPOINT ["java","-jar","app/wallet-service-0.0.1-SNAPSHOT.jar"]

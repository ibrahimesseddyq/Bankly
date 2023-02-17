FROM openjdk:11
WORKDIR /app
COPY ./target/gateway-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888:8888
ENTRYPOINT["java","-jar","app.jar"]

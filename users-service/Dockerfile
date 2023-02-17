FROM openjdk:11
WORKDIR /app
COPY ./target/users-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8091:8091
ENTRYPOINT["java","-jar","app.jar"]

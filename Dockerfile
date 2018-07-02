FROM openjdk:8-jdk-alpine
EXPOSE 8080/tcp
ADD /target/fizzbuzz-0.0.1-SNAPSHOT.war app.jar
ENTRYPOINT ["java","-jar","app.jar"]
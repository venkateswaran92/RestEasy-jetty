FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/RestEasy-jetty-3.0.jar RestEasy-jetty-3.0.jar
ENTRYPOINT ["sh", "-c", "java -jar RestEasy-jetty-3.0.jar"]

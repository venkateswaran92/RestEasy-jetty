FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/RestEasy-jetty-3.0.jar.jar RestEasy-jetty-3.0.jar.jar
ENTRYPOINT ["sh", "-c", "java -jar RestEasy-jetty-3.0.jar.jar"]
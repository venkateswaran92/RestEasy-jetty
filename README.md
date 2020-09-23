Getting started with RESTEasy
=============================
A step-by-step introduction to the RESTEasy framework with jetty server.

System Requirements:
--------------------
- OpenJDK for Java 1.8
- Maven 3.3.9 or higher

Building the example project:
-----------------------------

To build the fat JAR and run some tests:

    mvn clean install

To run:

    java -jar target/jetty-swagger-3.0.jar

Employee:

    http://localhost:8080/api/employee/1

Swagger:

    http://localhost:8080/api/swagger.json

Swagger UI:

    http://localhost:8080


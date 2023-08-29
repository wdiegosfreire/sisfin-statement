FROM openjdk:11
VOLUME /tmp
COPY ./target/*.jar sisfin-statement.jar
ENTRYPOINT ["java","-jar","/sisfin-statement.jar"]
FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY /target/spring-boot.jar .
ENTRYPOINT ["java","-jar","/spring-boot.jar"]

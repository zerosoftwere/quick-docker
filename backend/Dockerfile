FROM openjdk:11.0.8-jre-slim

VOLUME /tmp

COPY target/contacts-1.0.0-SNAPSHOT-runner.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
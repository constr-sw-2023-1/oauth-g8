FROM openjdk:17-alpine

EXPOSE 8080

COPY target/oauth-0.0.1-SNAPSHOT.jar oauth-0.0.1.jar

ENTRYPOINT [ "java", "-jar", "oauth-0.0.1.jar" ]
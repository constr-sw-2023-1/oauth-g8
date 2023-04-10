FROM maven:3.8.3-openjdk-17 AS compile

COPY . .

RUN mvn clean install -Dmaven.test.skip

FROM openjdk:17-alpine

EXPOSE 8143

COPY --from=compile target/oauth-0.0.1-SNAPSHOT.jar oauth-0.0.1.jar

ENTRYPOINT [ "java", "-jar", "oauth-0.0.1.jar" ]
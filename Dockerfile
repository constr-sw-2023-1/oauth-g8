FROM adoptopenjdk/maven-openjdk11:latest AS compile

COPY . .

RUN mvn clean package -Dmaven.test.skip=true -q

FROM openjdk:17-alpine

EXPOSE 8080

COPY --from=compile target/oauth-0.0.1-SNAPSHOT.jar oauth-0.0.1.jar

ENTRYPOINT [ "java", "-jar", "oauth-0.0.1.jar" ]
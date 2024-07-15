FROM openjdk:17.0.2-slim

ENV TZ=Asia/Seoul

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

CMD ["java", "-jar", "app.jar", "--spring.config.location=file:/config/application.yml"]
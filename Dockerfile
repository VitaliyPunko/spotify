FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY build/libs/music-event-core.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]



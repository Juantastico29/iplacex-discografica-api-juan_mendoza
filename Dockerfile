FROM gradle:jdk21 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY src ./src
RUN gradle bootJar --no-daemon -x test

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb+srv://mendozapadilla_db_user:juanfe001@eva-u2-spring.brcuerv.mongodb.net/discografica-db?retryWrites=true&w=majority&appName=eva-u2-spring", "-jar", "app.jar"]

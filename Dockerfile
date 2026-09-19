# ETAPA 1: Construcción del archivo JAR con Gradle y JDK 21
FROM gradle:jdk21 AS build
WORKDIR /app

# Copiar archivos de configuración y dependencias
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Copiar el código fuente
COPY src ./src

# Compilar y empaquetar el proyecto omitiendo los tests
RUN gradle bootJar --no-daemon -x test

# Renombrar automáticamente el .jar principal a app.jar descartando el plain.jar
RUN cp $(ls /app/build/libs/*.jar | grep -v plain) /app/app.jar

# ETAPA 2: Entorno de ejecución ligero con OpenJDK 21
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiar el archivo app.jar listo desde la etapa de compilación
COPY --from=build /app/app.jar app.jar

# Exponer el puerto de Spring Boot
EXPOSE 8080

# Iniciar la API
ENTRYPOINT ["java", "-jar", "app.jar"]
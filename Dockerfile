# ETAPA 1: Compilación del archivo JAR con Gradle y JDK 21
FROM gradle:jdk21 AS build
WORKDIR /app

# Copiar archivos de configuración y dependencias
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Copiar el código fuente del proyecto
COPY src ./src

# Compilar y empaquetar el proyecto omitiendo pruebas
RUN gradle bootJar --no-daemon -x test

# Renombrar automáticamente el archivo .jar ejecutable a app.jar
RUN find /app/build/libs -name "*.jar" ! -name "*plain*" -exec cp {} /app/app.jar \;

# ETAPA 2: Entorno de ejecución ligero con OpenJDK 21
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiar el archivo app.jar listo desde la etapa de compilación
COPY --from=build /app/app.jar app.jar

# Exponer el puerto de Spring Boot
EXPOSE 8080

# Iniciar la API
ENTRYPOINT ["java", "-jar", "app.jar"]
# Etapa de construcción
FROM alpine:latest as build

# Actualizar y agregar dependencias
RUN apk update && apk add --no-cache openjdk17

# Copiar el proyecto y construir el JAR
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

# Etapa de ejecución
FROM openjdk:17-alpine

# Definir el directorio de trabajo
WORKDIR /app

# Exponer el puerto de la aplicación
EXPOSE 9000

# Copiar el JAR construido desde la etapa de construcción
COPY --from=build ./build/libs/inicial1-0.0.1-SNAPSHOT.jar ./app.jar

# Definir el usuario no root para ejecutar la aplicación
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Definir el punto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]

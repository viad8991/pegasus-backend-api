# Затыячка на первое время.
FROM ubuntu

# Этап 1: Сборка приложения
# FROM gradle:8.7-jdk17 AS builder
# WORKDIR /app
# COPY build.gradle.kts .
# COPY settings.gradle.kts .
# COPY src ./src
# RUN gradle build --no-daemon
#
# # Этап 2: Запуск приложения
# FROM openjdk:17
# WORKDIR /app
# COPY --from=builder /app/build/libs/*.jar app.jar
# ENTRYPOINT ["java", "-jar", "app.jar"]

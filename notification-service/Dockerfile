# Используем минимальный образ с Java 21 и JDK
FROM eclipse-temurin:21-jre-alpine AS base

# Указываем рабочую директорию
WORKDIR /app

# Копируем скомпилированный JAR в контейнер
COPY target/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar

# Указываем порт
EXPOSE 8083

# Используем exec-формат ENTRYPOINT для стабильности
ENTRYPOINT ["java", "-jar", "/app/notification-service.jar"]

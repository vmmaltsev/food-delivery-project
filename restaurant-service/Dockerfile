# Используем минимальный образ с Java 21 и JDK
FROM eclipse-temurin:21-jre-alpine AS base

# Указываем рабочую директорию
WORKDIR /app

# Копируем скомпилированный JAR в контейнер
COPY target/restaurant-service-0.0.1-SNAPSHOT.jar restaurant-service.jar

# Указываем порт
EXPOSE 8081

# Используем exec-формат ENTRYPOINT для стабильности
ENTRYPOINT ["java", "-jar", "/app/restaurant-service.jar"]

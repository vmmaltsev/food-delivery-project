# 🔹 Этап 1: Сборка фронтенд-приложения
FROM node:21-alpine AS build-stage

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем package.json и package-lock.json для кеширования зависимостей
COPY package*.json ./

# Устанавливаем зависимости с кешированием (чтобы `node_modules` не пересоздавался при изменении кода)
RUN npm ci

# Копируем весь код после установки зависимостей (ускоряет сборку)
COPY . .

# Собираем приложение
RUN npm run build

# 🔹 Этап 2: Оптимизированный Nginx-сервер
FROM nginx:stable-alpine

# Удаляем стандартную конфигурацию Nginx и добавляем свою
RUN rm -rf /etc/nginx/conf.d
COPY nginx.conf /etc/nginx/nginx.conf

# Копируем собранные файлы из предыдущего этапа
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Открываем порт 80
EXPOSE 80

# Запускаем Nginx
CMD ["nginx", "-g", "daemon off;"]

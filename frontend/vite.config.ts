import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true, // Позволяет доступ к Vite из Docker
    port: 5173, // Запуск на 5173 порту
  },
});

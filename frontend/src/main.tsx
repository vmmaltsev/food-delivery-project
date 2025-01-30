import { StrictMode, Suspense } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";

const rootElement = document.getElementById("root");

if (!rootElement) {
  console.error("❌ Ошибка: Не найден элемент с id='root'. Проверьте index.html.");
} else {
  const root = createRoot(rootElement);

  root.render(
    import.meta.env.MODE === "development" ? (
      <StrictMode>
        <Suspense fallback={<p>⏳ Загрузка приложения...</p>}>
          <App />
        </Suspense>
      </StrictMode>
    ) : (
      <Suspense fallback={<p>⏳ Загрузка приложения...</p>}>
        <App />
      </Suspense>
    )
  );
}

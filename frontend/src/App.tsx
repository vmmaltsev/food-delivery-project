import { useState, useEffect } from "react";
import axios from "axios";
import { motion } from "framer-motion";
import { FaHamburger, FaExclamationCircle } from "react-icons/fa";

interface Restaurant {
  id: number;
  name: string;
  address: string;
}

// Создаём API клиент
const api = axios.create({
  baseURL: "/api",
});

function App() {
  const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [orderStatus, setOrderStatus] = useState<string | null>(null);

  useEffect(() => {
    api
      .get<Restaurant[]>("/restaurants")
      .then((res) => setRestaurants(res.data))
      .catch((err) => {
        console.error("Ошибка при загрузке ресторанов:", err);
        setError("Не удалось загрузить список ресторанов.");
      })
      .finally(() => setLoading(false));
  }, []);

  // Функция для создания заказа
  const createOrder = async (restaurantId: string) => {
    const userId = "user123"; // Заглушка, можно заменить на текущего пользователя

    try {
      const response = await api.post("/orders", { userId, restaurantId });
      setOrderStatus(`✅ Заказ создан! ID заказа: ${response.data.id}`);
    } catch (error) {
      console.error("Ошибка при создании заказа:", error);
      setOrderStatus("❌ Ошибка при создании заказа.");
    }

    // Очистка сообщения через 3 секунды
    setTimeout(() => setOrderStatus(null), 3000);
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-br from-gray-100 to-gray-200 p-6 text-gray-900">
      {/* Заголовок с анимацией */}
      <motion.h1
        className="text-4xl font-bold text-gray-800 mb-6 flex items-center gap-3"
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.7 }}
      >
        <FaHamburger className="text-yellow-500" /> Food Delivery - Рестораны
      </motion.h1>

      {/* Лоадер */}
      {loading && (
        <motion.div
          className="flex space-x-2"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ duration: 0.5 }}
        >
          <div className="w-5 h-5 bg-gray-300 rounded-full animate-bounce"></div>
          <div className="w-5 h-5 bg-gray-400 rounded-full animate-bounce delay-150"></div>
          <div className="w-5 h-5 bg-gray-500 rounded-full animate-bounce delay-300"></div>
        </motion.div>
      )}

      {/* Ошибка */}
      {error && (
        <motion.div
          className="flex items-center gap-2 text-red-600 bg-red-200 px-4 py-2 rounded-md mt-4 shadow-md"
          initial={{ opacity: 0, y: -10 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
        >
          <FaExclamationCircle className="text-red-600 text-xl" />
          <p className="text-lg font-medium">{error}</p>
        </motion.div>
      )}

      {/* Список ресторанов */}
      {!loading && !error && (
        <motion.ul
          className="bg-white shadow-lg rounded-lg p-6 w-full max-w-2xl"
          initial={{ opacity: 0, scale: 0.9 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ duration: 0.5 }}
        >
          {restaurants.length > 0 ? (
            restaurants.map((r) => (
              <motion.li
                key={r.id}
                className="bg-gray-50 border border-gray-300 rounded-md py-4 px-6 flex justify-between items-center shadow-md hover:shadow-lg transition-all duration-200 cursor-pointer"
                whileHover={{ scale: 1.02 }}
                whileTap={{ scale: 0.98 }}
              >
                <div>
                  <span className="text-lg font-semibold">{r.name}</span>
                  <span className="block text-gray-600 text-sm">{r.address}</span>
                </div>
                <button
                  onClick={() => createOrder(r.id.toString())}
                  className="ml-4 bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition"
                >
                  Заказать
                </button>
              </motion.li>
            ))
          ) : (
            <p className="text-gray-500 text-center">📭 Нет доступных ресторанов.</p>
          )}
        </motion.ul>
      )}

      {/* Сообщение о статусе заказа */}
      {orderStatus && (
        <motion.div
          className="mt-6 p-4 bg-green-100 text-green-700 rounded-lg shadow-md"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ duration: 0.5 }}
        >
          {orderStatus}
        </motion.div>
      )}
    </div>
  );
}

export default App;

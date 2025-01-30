package com.maltsevtech.fooddelivery.notification.listener;

import com.maltsevtech.fooddelivery.notification.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderCreatedListener {

    @KafkaListener(topics = "order_created", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload OrderDTO order) { // ✅ Теперь слушаем JSON
        try {
            log.info("📦 Новый заказ создан! ID: {}", order.getId());

            // Логика отправки Email/SMS
            sendNotification(order);

        } catch (Exception e) {
            log.error("❌ Ошибка обработки заказа ID: {}", order.getId(), e);
        }
    }

    private void sendNotification(OrderDTO order) {
        log.info("✉️ Отправляем уведомление о заказе ID: {} клиенту {}", order.getId(), order.getUserId());
    }
}

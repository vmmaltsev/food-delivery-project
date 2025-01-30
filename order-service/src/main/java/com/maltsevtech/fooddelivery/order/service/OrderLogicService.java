package com.maltsevtech.fooddelivery.order.service;

import com.maltsevtech.fooddelivery.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLogicService {

    private static final Logger logger = LoggerFactory.getLogger(OrderLogicService.class);
    private static final String TOPIC = "order_created"; // ✅ Исправлено название топика

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrderCreatedEvent(Order order) {
        kafkaTemplate.send(TOPIC, order.getId().toString(), order)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        logger.error("❌ Ошибка отправки сообщения в Kafka ({}): {}", TOPIC, ex.getMessage(), ex);
                    } else {
                        logger.info("✅ Сообщение отправлено в Kafka ({}): {}", TOPIC, order);
                    }
                });
    }
}

package com.maltsevtech.fooddelivery.order.config;

import com.maltsevtech.fooddelivery.order.repository.OrderRepository;
import com.maltsevtech.fooddelivery.order.model.Order;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@TestConfiguration
public class TestConfig {

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    @SuppressWarnings("unchecked") // Подавляем предупреждение о generic-типа
    public KafkaTemplate<String, Order> kafkaTemplate() {
        return (KafkaTemplate<String, Order>) Mockito.mock(KafkaTemplate.class);
    }
}

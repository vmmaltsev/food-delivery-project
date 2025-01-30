package com.maltsevtech.fooddelivery.order.controller;

import com.maltsevtech.fooddelivery.order.dto.OrderRequestDTO;
import com.maltsevtech.fooddelivery.order.dto.OrderResponseDTO;
import com.maltsevtech.fooddelivery.order.model.Order;
import com.maltsevtech.fooddelivery.order.repository.OrderRepository;
import com.maltsevtech.fooddelivery.order.service.OrderLogicService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderRepository orderRepository;
    private final OrderLogicService orderLogicService;
    private final ModelMapper modelMapper;

    /**
     * Получить все заказы.
     *
     * @return список заказов
     */
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDTO> response = orders.stream()
                .map(order -> modelMapper.map(order, OrderResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Получить заказ по ID.
     *
     * @param id идентификатор заказа
     * @return заказ
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> ResponseEntity.ok(modelMapper.map(order, OrderResponseDTO.class)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    /**
     * Создать новый заказ.
     *
     * @param orderRequestDTO объект заказа из запроса
     * @return сохраненный заказ
     */
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Validated @RequestBody OrderRequestDTO orderRequestDTO) {
        // Создаём новый объект Order вручную (без ModelMapper)
        Order order = new Order();
        order.setUserId(orderRequestDTO.getUserId());
        order.setRestaurantId(orderRequestDTO.getRestaurantId());
        order.setStatus(Order.OrderStatus.PENDING); // Устанавливаем статус по умолчанию
        order.setCreatedAt(LocalDateTime.now()); // Устанавливаем дату создания

        // Сохраняем заказ в БД
        Order savedOrder = orderRepository.save(order);

        // Отправляем событие в Kafka
        try {
            orderLogicService.sendOrderCreatedEvent(savedOrder);
            logger.info("✅ Заказ создан и отправлен в Kafka: {}", savedOrder);
        } catch (Exception e) {
            logger.error("❌ Ошибка при отправке заказа в Kafka: {}", e.getMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedOrder, OrderResponseDTO.class));
    }
}

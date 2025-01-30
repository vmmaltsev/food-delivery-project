package com.maltsevtech.fooddelivery.order.dto;

import com.maltsevtech.fooddelivery.order.model.Order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDTO {
    private Long id;
    private String userId;
    private String restaurantId;
    private OrderStatus status;
    private LocalDateTime createdAt;
}

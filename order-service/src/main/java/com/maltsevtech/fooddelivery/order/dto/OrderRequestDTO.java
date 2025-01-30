package com.maltsevtech.fooddelivery.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequestDTO {

    @NotBlank(message = "userId не может быть пустым")
    private String userId;

    @NotBlank(message = "restaurantId не может быть пустым")
    private String restaurantId;
}

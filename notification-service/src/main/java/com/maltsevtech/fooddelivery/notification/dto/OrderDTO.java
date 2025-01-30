package com.maltsevtech.fooddelivery.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("restaurantId")
    private String restaurantId;

    @JsonProperty("status")
    private String status;
}

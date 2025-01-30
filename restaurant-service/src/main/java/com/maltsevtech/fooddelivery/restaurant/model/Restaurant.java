package com.maltsevtech.fooddelivery.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название ресторана не может быть пустым")
    @Size(max = 100, message = "Название ресторана не может быть длиннее 100 символов")
    private String name;

    @NotBlank(message = "Адрес ресторана не может быть пустым")
    @Size(max = 255, message = "Адрес ресторана не может быть длиннее 255 символов")
    private String address;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

package com.maltsevtech.fooddelivery.restaurant.repository;

import com.maltsevtech.fooddelivery.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Найти ресторан по названию (игнорируя регистр)
    Optional<Restaurant> findByNameIgnoreCase(String name);

    // Найти рестораны по части названия
    List<Restaurant> findByNameContainingIgnoreCase(String name);

    // Найти рестораны по адресу (полный адрес)
    List<Restaurant> findByAddressIgnoreCase(String address);

    // Найти рестораны, содержащие определенный текст в адресе
    List<Restaurant> findByAddressContainingIgnoreCase(String address);
}

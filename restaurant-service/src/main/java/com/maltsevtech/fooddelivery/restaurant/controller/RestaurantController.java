package com.maltsevtech.fooddelivery.restaurant.controller;

import com.maltsevtech.fooddelivery.restaurant.model.Restaurant;
import com.maltsevtech.fooddelivery.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // Получить все рестораны
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        log.info("Получение списка всех ресторанов");
        return restaurantService.getAllRestaurants();
    }

    // Создать новый ресторан
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurantInput) {
        log.info("Создание нового ресторана: {}", restaurantInput.getName());
        return restaurantService.createRestaurant(restaurantInput);
    }

    // Найти ресторан по ID
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        log.info("Поиск ресторана по ID: {}", id);
        return restaurantService.getRestaurantById(id);
    }

    // Найти ресторан по названию (поиск без учета регистра)
    @GetMapping("/search/name")
    public List<Restaurant> searchByName(@RequestParam String name) {
        log.info("Поиск ресторанов по имени: {}", name);
        return restaurantService.searchByName(name);
    }

    // Найти ресторан по адресу
    @GetMapping("/search/address")
    public List<Restaurant> searchByAddress(@RequestParam String address) {
        log.info("Поиск ресторанов по адресу: {}", address);
        return restaurantService.searchByAddress(address);
    }

    // Удалить ресторан по ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Long id) {
        log.info("Удаление ресторана по ID: {}", id);
        restaurantService.deleteRestaurant(id);
    }
}

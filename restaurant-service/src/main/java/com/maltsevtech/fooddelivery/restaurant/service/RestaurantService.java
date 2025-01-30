package com.maltsevtech.fooddelivery.restaurant.service;

import com.maltsevtech.fooddelivery.restaurant.model.Restaurant;
import com.maltsevtech.fooddelivery.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // Получить все рестораны
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Создать новый ресторан
    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Найти ресторан по ID
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Ресторан не найден"));
    }

    // Найти ресторан по названию
    public List<Restaurant> searchByName(String name) {
        return restaurantRepository.findByNameContainingIgnoreCase(name);
    }

    // Найти ресторан по адресу
    public List<Restaurant> searchByAddress(String address) {
        return restaurantRepository.findByAddressContainingIgnoreCase(address);
    }

    // Удалить ресторан по ID
    @Transactional
    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Ресторан не найден");
        }
        restaurantRepository.deleteById(id);
    }
}

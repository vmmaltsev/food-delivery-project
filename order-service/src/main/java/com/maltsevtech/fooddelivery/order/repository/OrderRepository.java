package com.maltsevtech.fooddelivery.order.repository;

import com.maltsevtech.fooddelivery.order.model.Order;
import com.maltsevtech.fooddelivery.order.model.Order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Поиск заказов по ID пользователя
    List<Order> findByUserId(String userId);

    // Поиск заказов по ID ресторана
    List<Order> findByRestaurantId(String restaurantId);

    // Поиск заказов по статусу
    List<Order> findByStatus(OrderStatus status);

    // Поиск последнего заказа пользователя
    Optional<Order> findTopByUserIdOrderByCreatedAtDesc(String userId);

    // Кастомный JPQL-запрос для поиска заказов по статусу и пользователю
    @Query("SELECT o FROM Order o WHERE o.userId = :userId AND o.status = :status")
    List<Order> findOrdersByUserIdAndStatus(String userId, OrderStatus status);
}

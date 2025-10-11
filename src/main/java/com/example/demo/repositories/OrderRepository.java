package com.example.demo.repositories;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Order;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 🔹 Get all orders placed by a specific user
    List<Order> findByUser(Long userId);

    // 🔹 Get all orders of a user by status (e.g. "DELIVERED", "PENDING")
    List<Order> findByUserAndOrderStatus(User user, String status);

    // 🔹 Get all orders for a particular restaurant
    List<Order> findByRestaurant(Restaurant restaurant);

    // 🔹 Get all orders for a restaurant by order status (for restaurant dashboard)
    List<Order> findByRestaurantAndOrderStatus(Restaurant restaurant, String status);

    // 🔹 Find a specific order by ID and user (for security, ensure it belongs to them)
    Optional<Order> findByIdAndUser(Long orderId, User user);

    // 🔹 Get all orders by payment status (useful for admin/analytics)
    List<Order> findByPaymentStatus(String paymentStatus);

    // 🔹 Get recent orders (sorted by created time descending)
    @Query("SELECT o FROM Order o WHERE o.user = :user ORDER BY o.createdAt DESC")
    List<Order> findRecentOrdersByUser(@Param("user") User user);

    // 🔹 Get all delivered orders for a restaurant (for revenue reports)
    @Query("SELECT o FROM Order o WHERE o.restaurant = :restaurant AND o.orderStatus = 'DELIVERED'")
    List<Order> findDeliveredOrdersByRestaurant(@Param("restaurant") Restaurant restaurant);
}
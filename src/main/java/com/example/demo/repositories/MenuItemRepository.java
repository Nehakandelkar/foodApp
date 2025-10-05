package com.example.demo.repositories;

import com.example.demo.entities.MenuItem;
import com.example.demo.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // 🔹 Get all menu items of a specific restaurant
    List<MenuItem> findByRestaurant(Restaurant restaurant);

    // 🔹 Get all menu items by restaurant ID
    List<MenuItem> findByRestaurantId(Long restaurantId);

    // 🔹 Search menu items by name (case-insensitive)
    List<MenuItem> findByNameContainingIgnoreCase(String name);

    // 🔹 Get all menu items by category (e.g., "Dessert", "Beverages", "Main Course")
    List<MenuItem> findByCategoryIgnoreCase(String category);

    // 🔹 Get all menu items within a price range
    List<MenuItem> findByPriceBetween(Double minPrice, Double maxPrice);

    // 🔹 Custom query: Get top-selling or most popular items (if you track order counts)
    @Query("SELECT m FROM MenuItem m ORDER BY m.timesOrdered DESC")
    List<MenuItem> findTopSellingItems();

    // 🔹 Custom query: Get menu items by restaurant and category
    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :restaurantId AND LOWER(m.category) = LOWER(:category)")
    List<MenuItem> findByRestaurantAndCategory(@Param("restaurantId") Long restaurantId, @Param("category") String category);
}

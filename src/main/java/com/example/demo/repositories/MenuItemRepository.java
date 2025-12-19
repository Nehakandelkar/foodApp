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

}

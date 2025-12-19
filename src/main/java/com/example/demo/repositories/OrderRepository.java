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
    List<Order> findByUser(User user);


}
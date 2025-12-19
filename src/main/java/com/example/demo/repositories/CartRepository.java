package com.example.demo.repositories;

import com.example.demo.entities.Cart;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 🔹 Find cart by user
    Optional<Cart> findByUser(User user);

    // 🔹 Find cart by user ID (handy for API calls)
    Optional<Cart> findByUserId(Long userId);


}

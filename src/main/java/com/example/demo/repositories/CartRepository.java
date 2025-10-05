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

    // 🔹 Check if a cart exists for a user
    boolean existsByUserId(Long userId);

    // 🔹 Delete a user’s cart (after checkout)
    @Transactional
    void deleteByUser(User user);

    // 🔹 Custom: clear all items in cart (without deleting the cart itself)
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_items WHERE cart_id = :cartId", nativeQuery = true)
    void clearItems(@Param("cartId") Long cartId);
}

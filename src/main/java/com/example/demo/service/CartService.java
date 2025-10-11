package com.example.demo.service;

import com.example.demo.entities.Cart;
import com.example.demo.entities.MenuItem;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.MenuItemRepository;

import java.awt.*;

public class CartService {

    private CartRepository cartRepository;

    private MenuItemRepository menuItemRepository;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            return cartRepository.save(newCart);
        });
    }

    public Cart addItemToCart(Long userId, Long menuItemId, int quantity) {
        Cart cart = getCartByUserId(userId);

        MenuItem item = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found."));

        for (int i = 0; i < quantity; i++) {
            cart.addItem(item);
        }

        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long userId, Long menuItemId) {
        Cart cart = getCartByUserId(userId);

        // Find the MenuItem in the cart
        MenuItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getId().equals(menuItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Menu item not found in cart"));

        cart.removeItem(itemToRemove);

        return cartRepository.save(cart);
    }

    // Clear all items from cart
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.clearCart();
        cartRepository.save(cart);
    }
}

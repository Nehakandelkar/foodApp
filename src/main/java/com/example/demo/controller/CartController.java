package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCartByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addItemToCart(
            @RequestParam Long userId,
            @RequestParam Long menuItemId,
            @RequestParam(defaultValue = "1") int quantity
    ) {
        return ResponseEntity.ok(cartService.addItemToCart(userId, menuItemId, quantity));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartDTO> removeItemFromCart(
            @RequestParam Long userId,
            @RequestParam Long menuItemId
    ) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, menuItemId));
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}

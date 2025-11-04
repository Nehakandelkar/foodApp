package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place/{userId}")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.placeOrder(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
}

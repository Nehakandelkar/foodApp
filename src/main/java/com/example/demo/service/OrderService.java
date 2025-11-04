package com.example.demo.service;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public Order placeOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        
        if(cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty.");
        }
        
        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(cart.getItems().get(0).getRestaurant()); // pick restaurant from first item
        order.setItems(cart.getItems());
        order.setTotalAmount(cart.getAmount());
        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);

        cart.clearCart();
        cartRepository.save(cart);

        return savedOrder;
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUser(userId);
    }

    // Update the status of an order
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

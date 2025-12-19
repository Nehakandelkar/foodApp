package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // ✅ Place a new order (takes userId, returns OrderDTO)
    public OrderDTO placeOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(cart.getItems().get(0).getRestaurant()); // assume single restaurant
        order.setItems(cart.getItems());
        order.setTotalAmount(cart.getAmount());
        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);

        // Clear cart after placing order
        cart.clearCart();
        cartRepository.save(cart);

        return convertToDTO(savedOrder);
    }

    public List<OrderDTO> getOrdersByUser(User user) {
        List<Order> orders = orderRepository.findByUser(user);
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO convertToDTO(Order entity) {
        return modelMapper.map(entity, OrderDTO.class);
    }

    public Order convertToEntity(OrderDTO dto) {
        return modelMapper.map(dto, Order.class);
    }
}

package com.example.demo.service;

import com.example.demo.dto.CartDTO;
import com.example.demo.entities.Cart;
import com.example.demo.entities.MenuItem;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.MenuItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            return cartRepository.save(newCart);
        });
        return convertToDTO(cart);
    }

    public CartDTO addItemToCart(Long userId, Long menuItemId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(new Cart()));

        MenuItem item = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        for (int i = 0; i < quantity; i++) {
            cart.addItem(item);
        }

        Cart savedCart = cartRepository.save(cart);
        return convertToDTO(savedCart);
    }

    public CartDTO removeItemFromCart(Long userId, Long menuItemId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        MenuItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getId().equals(menuItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        cart.removeItem(itemToRemove);
        Cart savedCart = cartRepository.save(cart);

        return convertToDTO(savedCart);
    }

    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.clearCart();
        cartRepository.save(cart);
    }

    private CartDTO convertToDTO(Cart entity) {
        return modelMapper.map(entity, CartDTO.class);
    }

    private Cart convertToEntity(CartDTO dto) {
        return modelMapper.map(dto, Cart.class);
    }
}

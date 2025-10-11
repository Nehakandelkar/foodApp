package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    private List<MenuItem> items = new ArrayList<>();;

    private Double amount = 0.0;

    public void addItem(MenuItem item) {

        if(items == null)  items = new ArrayList<>();

        items.add(item);
        recalculateAmount();
    }

    public void removeItem(MenuItem item) {
        if(items != null) {
            items.remove(item);
            recalculateAmount();
        }
    }

    public void clearCart() {
        if(items != null) {
            items.clear();
            amount = 0.0;
        }
    }

    private void recalculateAmount() {
        amount = items.stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();
    }
}

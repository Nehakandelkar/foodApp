package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //many orders one user
    private User user;

    @ManyToOne //many orders one restaurant
    private Restaurant restaurant;

    @OneToMany
    private List<MenuItem> items;

    private Double totalAmount;

    private String status;
}
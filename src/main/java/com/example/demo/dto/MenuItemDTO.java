package com.example.demo.dto;

import lombok.Data;

@Data
public class MenuItemDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Long restaurantID;
}

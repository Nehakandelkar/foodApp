package com.example.demo.dto;

import lombok.Data;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String location;
    private String cuisineType;
    private Double rating;
}

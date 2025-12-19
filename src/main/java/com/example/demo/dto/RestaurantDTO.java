package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RestaurantDTO {
    private Long id;
    private String name;
    private String location;
    private String cuisineType;
    private Double rating;
}

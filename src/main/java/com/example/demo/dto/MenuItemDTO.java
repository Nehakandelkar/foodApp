package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MenuItemDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Long restaurantID;
}

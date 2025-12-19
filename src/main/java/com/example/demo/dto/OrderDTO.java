package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long restaurantId;
    private List<MenuItemDTO> items;
    private Double totalAmount;
    private String status;
}

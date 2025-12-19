package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CartDTO {
    private Long id;
    private Long userId;
    private List<MenuItemDTO> items;
    private Double totalAmount;
}

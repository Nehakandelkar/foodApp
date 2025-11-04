package com.example.demo.controller;

import com.example.demo.dto.MenuItemRequestDTO;
import com.example.demo.dto.MenuItemResponseDTO;
import com.example.demo.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItemResponseDTO> addMenuItem(@RequestBody MenuItemRequestDTO dto) {
        return ResponseEntity.ok(menuItemService.addMenuItem(dto));
    }

    @GetMapping
    public ResponseEntity<List<MenuItemResponseDTO>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponseDTO> getMenuItemById(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponseDTO> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemRequestDTO dto) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}

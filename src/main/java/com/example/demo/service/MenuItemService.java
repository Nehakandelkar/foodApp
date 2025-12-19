package com.example.demo.service;

import com.example.demo.dto.MenuItemDTO;
import com.example.demo.entities.MenuItem;
import com.example.demo.repositories.MenuItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MenuItemDTO> getItemsByRestaurant(Long restaurantId) {
        List<MenuItem> items = menuItemRepository.findByRestaurantId(restaurantId);
        return items.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public MenuItemDTO addMenuItem(MenuItemDTO dto) {
        MenuItem entity = convertToEntity(dto);
        MenuItem saved = menuItemRepository.save(entity);
        return convertToDTO(saved);
    }

    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO dto) {
        MenuItem entity = convertToEntity(dto);
        entity.setId(id);
        MenuItem updated = menuItemRepository.save(entity);
        return convertToDTO(updated);
    }

    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new RuntimeException("Menu item not found with id: " + id);
        }
        menuItemRepository.deleteById(id);
    }

    private MenuItemDTO convertToDTO(MenuItem entity) {
        return modelMapper.map(entity, MenuItemDTO.class);
    }

    private MenuItem convertToEntity(MenuItemDTO dto) {
        return modelMapper.map(dto, MenuItem.class);
    }
}

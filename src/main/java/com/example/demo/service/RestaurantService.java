package com.example.demo.service;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantDTO addRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = convertToEntity(dto);
        Restaurant saved = restaurantRepository.save(restaurant);
        return convertToDTO(saved);
    }

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<RestaurantDTO> getRestaurantById(Long id) {
        return restaurantRepository.findById(id).map(this::convertToDTO);
    }

    public RestaurantDTO updateRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = convertToEntity(dto);
        Restaurant updated = restaurantRepository.save(restaurant);
        return convertToDTO(updated);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    private RestaurantDTO convertToDTO(Restaurant entity) {
        return modelMapper.map(entity, RestaurantDTO.class);
    }

    private Restaurant convertToEntity(RestaurantDTO dto) {
        return modelMapper.map(dto, Restaurant.class);
    }
}

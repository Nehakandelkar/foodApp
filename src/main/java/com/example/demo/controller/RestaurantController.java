package com.example.demo.controller;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // ✅ Create a restaurant
    @PostMapping
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO saved = restaurantService.addRestaurant(restaurantDTO);
        return ResponseEntity.ok(saved);
    }

    // ✅ Get all restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    // ✅ Get restaurant by ID
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update restaurant details
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO restaurantDTO) {
        restaurantDTO.setId(id); // ensure ID consistency
        RestaurantDTO updated = restaurantService.updateRestaurant(restaurantDTO);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete a restaurant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ (Optional) Get restaurants by cuisine or location — useful for search filters
    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDTO>> searchRestaurants(
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String location
    ) {
        // You can later implement filtering logic in RestaurantService
        // e.g., restaurantRepository.findByCuisineOrLocation(cuisine, location)
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    // ✅ (Optional) Get menu for a restaurant (future endpoint)
    @GetMapping("/{id}/menu")
    public ResponseEntity<?> getMenuByRestaurant(@PathVariable Long id) {
        // Placeholder — will connect to MenuService later
        return ResponseEntity.ok("Menu items for restaurant ID: " + id);
    }
}

package com.example.demo.repositories;

import com.example.demo.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByName(String name);
    List<Restaurant> findByCity(String city);
    List<Restaurant> findByCuisineType(String cuisineType);
    List<Restaurant> findByIsOpenTrue(); // Find all currently open restaurants
    List<Restaurant> findByRatingGreaterThanEqual(Double rating); // Filter by rating

    boolean existsByName(String name);


}

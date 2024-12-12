package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.springframework.data.domain.*;

import java.util.Optional;

public interface IRestaurantPersistencePort {

    boolean existsByName(String name);
    boolean existsByNit(Integer nit);
    Restaurant saveRestaurant(Restaurant restaurant);
    Page<Restaurant> getAllRestaurants(Pageable pageable);

    Optional<Restaurant> getRestaurantById(Long id);
}

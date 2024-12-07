package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;

public interface IRestaurantPersistencePort {

    boolean existsByName(String name);
    boolean existsByNit(Integer nit);
    Restaurant saveRestaurant(Restaurant restaurant);
}

package com.hexagonal.microservicio_plazoleta.domain.usecase;

import com.hexagonal.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import com.hexagonal.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

public abstract class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        if (restaurantPersistencePort.existsByName(restaurant.getName())) {
            throw new IllegalArgumentException(UNIQUE_NAME);
        }
        if (restaurantPersistencePort.existsByNit(restaurant.getNit())) {
            throw new IllegalArgumentException(NIT_NAME);
        }
        restaurantPersistencePort.saveRestaurant(restaurant);
    }
}

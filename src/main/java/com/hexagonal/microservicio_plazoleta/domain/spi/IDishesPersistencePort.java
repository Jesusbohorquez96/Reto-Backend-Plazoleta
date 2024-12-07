package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;

import java.util.Optional;

public interface IDishesPersistencePort {

    void saveDishes(Dishes dishes);
    boolean isRestaurantOwnedByUser(Long restaurantId, Long ownerId);
    Optional<Dishes> findById(Long id);
    void updateDishStatus(Long dishId, Boolean active);
}

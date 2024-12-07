package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;

public interface IDishesPersistencePort {

    void saveDishes(Dishes dishes);
    boolean isRestaurantOwnedByUser(Long restaurantId, Long ownerId);
}

package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;

public interface IDishesServicePort {

    void saveDishes(Dishes dishes);

    void updateDish(Long dishId, Integer price, String description, Long ownerId);

    void updateDishStatus(Long dishId, Boolean active, Long ownerId);
}

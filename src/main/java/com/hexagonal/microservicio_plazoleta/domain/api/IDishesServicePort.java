package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import org.springframework.data.domain.*;

public interface IDishesServicePort {

    void saveDishes(Dishes dishes);

    void updateDish(Long dishId, Integer price, String description, Long ownerId);

    void updateDishStatus(Long dishId, Boolean active, Long ownerId);

    Page<Dishes> getDishesByRestaurantId(Long restaurantId, String category, Pageable pageable);
}

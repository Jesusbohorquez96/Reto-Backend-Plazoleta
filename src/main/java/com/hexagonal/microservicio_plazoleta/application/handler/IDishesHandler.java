package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.DishStatusUpdateRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.DishesRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.DishesUpdateRequest;

public interface IDishesHandler {

    void saveDishes (DishesRequest dishesRequest);

    void updateDish(Long dishId, DishesUpdateRequest request);

    void updateDishStatus(Long dishId, DishStatusUpdateRequest request);
}

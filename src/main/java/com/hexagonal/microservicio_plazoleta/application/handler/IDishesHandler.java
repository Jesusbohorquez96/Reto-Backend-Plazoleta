package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.DishesRequest;

public interface IDishesHandler {

    void saveDishes (DishesRequest dishesRequest);
}

package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;

public interface IDishesServicePort {

    void saveDishes(Dishes dishes);
}

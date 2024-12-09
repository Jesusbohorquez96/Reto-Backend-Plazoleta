package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.application.dto.SelectedDishRequest;

public interface ISelectedDishServicePort {

    void addSelectedDish(Long userId, SelectedDishRequest selectedDishRequest);
}

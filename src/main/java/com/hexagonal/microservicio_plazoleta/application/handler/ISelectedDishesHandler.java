package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.SelectedDishRequest;

public interface ISelectedDishesHandler {

    void addDishToSelection(Long userId, SelectedDishRequest selectedDishRequest);
}

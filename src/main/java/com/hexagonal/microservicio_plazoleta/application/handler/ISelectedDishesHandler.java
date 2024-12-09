package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.SelectedDishRequest;

import java.util.List;

public interface ISelectedDishesHandler {

    void addDishToSelection(Long userId, List<SelectedDishRequest> selectedDishRequest);
}

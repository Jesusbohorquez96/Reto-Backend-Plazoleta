package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.application.dto.SelectedDishRequest;

import java.util.List;

public interface ISelectedDishServicePort {

    void addSelectedDish(Long userId, List<SelectedDishRequest> selectedDishRequests);
}

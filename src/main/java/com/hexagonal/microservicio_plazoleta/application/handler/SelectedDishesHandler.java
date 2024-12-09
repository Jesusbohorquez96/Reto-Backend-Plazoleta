package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.SelectedDishRequest;
import com.hexagonal.microservicio_plazoleta.domain.api.ISelectedDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SelectedDishesHandler implements ISelectedDishesHandler{

    private final ISelectedDishServicePort selectedDishServicePort;

    @Override
    public void addDishToSelection(Long userId, SelectedDishRequest selectedDishRequest) {
        selectedDishServicePort.addSelectedDish(userId, selectedDishRequest);
    }
}

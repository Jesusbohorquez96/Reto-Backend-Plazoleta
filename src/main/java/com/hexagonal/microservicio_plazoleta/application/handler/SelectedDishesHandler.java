package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.SelectedDishRequest;
import com.hexagonal.microservicio_plazoleta.domain.api.ISelectedDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SelectedDishesHandler implements ISelectedDishesHandler{

    private final ISelectedDishServicePort selectedDishServicePort;

    @Override
    public void addDishToSelection(Long userId, List<SelectedDishRequest> selectedDishRequest) {
        selectedDishServicePort.addSelectedDish(userId, selectedDishRequest);
    }
}

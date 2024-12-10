package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.domain.api.ISelectedDishServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SelectedDishesHandler implements ISelectedDishesHandler{

    private final ISelectedDishServicePort selectedDishServicePort;

    @Override
    public void addDishesToOrder(Long userId, OrderRequest  orderRequest) {
        orderRequest.getSelectedDishes().forEach(request -> {
            SelectedDish selectedDish = new SelectedDish();
            selectedDishServicePort.saveSelectedDish(selectedDish);
        });
    }
}

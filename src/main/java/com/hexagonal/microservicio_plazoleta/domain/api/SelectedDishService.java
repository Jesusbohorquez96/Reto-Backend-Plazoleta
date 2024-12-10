package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.SelectedValidator;
import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.domain.spi.SelectedDishPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Service
@RequiredArgsConstructor
public class SelectedDishService {

    private final SelectedDishPersistencePort selectedDishPersistencePort;

    public List<SelectedDish> mapAndValidateDishes(List<Dishes> dishes, OrderRequest orderRequest, Order order) {
        if (order == null) {
            throw new IllegalArgumentException(ORDER_CANNOT_NULL);
        }

        return orderRequest.getSelectedDishes().stream()
                .map(request -> {
                    Dishes dish = dishes.stream()
                            .filter(d -> d.getId().equals(request.getDishId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException(DISH_NOT_FOUNT_ID + request.getDishId()));

                    if (!dish.isActive()) {
                        throw new IllegalArgumentException(DISH_NOT_ACTIVE + dish.getId());
                    }

                    return new SelectedDish(
                            null,
                            dish.getId(),
                            order.getUserId(),
                            request.getQuantity(),
                            SelectedValidator.calculateTotalPrice(dish.getPrice(), request.getQuantity()),
                            order
                    );
                }).collect(Collectors.toList());
    }

    public void saveSelectedDishes(List<SelectedDish> selectedDishes) {
        selectedDishes.forEach(selectedDishPersistencePort::saveSelectedDish);
    }
}

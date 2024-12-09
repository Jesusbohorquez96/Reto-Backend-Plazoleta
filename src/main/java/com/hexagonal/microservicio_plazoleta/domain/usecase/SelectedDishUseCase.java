package com.hexagonal.microservicio_plazoleta.domain.usecase;

import com.hexagonal.microservicio_plazoleta.application.dto.SelectedDishRequest;
import com.hexagonal.microservicio_plazoleta.domain.Utils.SelectedValidator;
import com.hexagonal.microservicio_plazoleta.domain.api.ISelectedDishServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.SelectedDishPersistencePort;

import java.util.List;
import java.util.stream.Collectors;

public class SelectedDishUseCase implements ISelectedDishServicePort {

    private final SelectedDishPersistencePort selectedDishPersistencePort;
    private final IDishesPersistencePort dishesPersistencePort;

    public SelectedDishUseCase(SelectedDishPersistencePort selectedDishPersistencePort, IDishesPersistencePort dishesPersistencePort) {
        this.selectedDishPersistencePort = selectedDishPersistencePort;
        this.dishesPersistencePort = dishesPersistencePort;
    }

    @Override
    public void addSelectedDish(Long userId, List<SelectedDishRequest> selectedDishRequests) {
        if (selectedDishRequests == null || selectedDishRequests.isEmpty()) {
            return;
        }

        List<Dishes> dishes = selectedDishRequests.stream()
                .map(request -> dishesPersistencePort.getDishById(request.getDishId()))
                .collect(Collectors.toList());

        SelectedValidator.validateSameRestaurant(dishes);

        selectedDishRequests.forEach(selectedDishRequest -> {
            Dishes dish = dishes.stream()
                    .filter(d -> d.getId().equals(selectedDishRequest.getDishId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

            SelectedValidator.validateDishIsActive(dish);

            Double totalPrice = SelectedValidator.calculateTotalPrice(dish.getPrice(), selectedDishRequest.getQuantity());

            SelectedDish selectedDish = new SelectedDish();
            selectedDish.setDishId(selectedDishRequest.getDishId());
            selectedDish.setUserId(userId);
            selectedDish.setQuantity(selectedDishRequest.getQuantity());
            selectedDish.setPrice(totalPrice);

            selectedDishPersistencePort.saveSelectedDish(selectedDish);
        });
    }
}

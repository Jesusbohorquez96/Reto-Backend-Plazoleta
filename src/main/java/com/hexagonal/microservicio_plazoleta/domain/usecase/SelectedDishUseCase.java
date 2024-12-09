package com.hexagonal.microservicio_plazoleta.domain.usecase;

import com.hexagonal.microservicio_plazoleta.application.dto.SelectedDishRequest;
import com.hexagonal.microservicio_plazoleta.domain.Utils.SelectedValidator;
import com.hexagonal.microservicio_plazoleta.domain.api.ISelectedDishServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.SelectedDishPersistencePort;

public class SelectedDishUseCase implements ISelectedDishServicePort {

    private final SelectedDishPersistencePort selectedDishPersistencePort;
    private final IDishesPersistencePort dishesPersistencePort;

    public SelectedDishUseCase(SelectedDishPersistencePort selectedDishPersistencePort, IDishesPersistencePort dishesPersistencePort) {
        this.selectedDishPersistencePort = selectedDishPersistencePort;
        this.dishesPersistencePort = dishesPersistencePort;
    }

    @Override
    public void addSelectedDish(Long userId, SelectedDishRequest selectedDishRequest) {
        Dishes dish = dishesPersistencePort.getDishById(selectedDishRequest.getDishId());

        SelectedValidator.validateDishIsActive(dish);

        Double TotalPrice = SelectedValidator.calculateTotalPrice(dish.getPrice(), selectedDishRequest.getQuantity());

        SelectedDish selectedDish = new SelectedDish();
        selectedDish.setDishId(selectedDishRequest.getDishId());
        selectedDish.setUserId(userId);
        selectedDish.setQuantity(selectedDishRequest.getQuantity());
        selectedDish.setPrice(TotalPrice);

        selectedDishPersistencePort.saveSelectedDish(selectedDish);
    }
}

package com.hexagonal.microservicio_plazoleta.domain.usecase;

import com.hexagonal.microservicio_plazoleta.domain.api.ISelectedDishServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.domain.spi.SelectedDishPersistencePort;

public class SelectedDishUseCase implements ISelectedDishServicePort {

    private final SelectedDishPersistencePort selectedDishPersistencePort;

    public SelectedDishUseCase(SelectedDishPersistencePort selectedDishPersistencePort) {
        this.selectedDishPersistencePort = selectedDishPersistencePort;
    }

    @Override
    public void saveSelectedDish(SelectedDish selectedDish) {
        selectedDishPersistencePort.saveSelectedDish(selectedDish);
    }
}

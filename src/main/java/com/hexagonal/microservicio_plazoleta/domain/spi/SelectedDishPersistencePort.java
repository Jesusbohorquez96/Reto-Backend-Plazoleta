package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;

public interface SelectedDishPersistencePort {

    void saveSelectedDish(SelectedDish selectedDish);
}

package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;

public interface ISelectedDishServicePort {

    void saveSelectedDish(SelectedDish selectedDish);
}

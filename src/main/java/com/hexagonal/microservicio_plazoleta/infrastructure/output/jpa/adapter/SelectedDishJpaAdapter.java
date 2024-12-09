package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.domain.spi.SelectedDishPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.SelectedDishesEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.SelectedDishesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SelectedDishJpaAdapter implements SelectedDishPersistencePort {

    private final SelectedDishesRepository selectedDishesRepository;

    @Override
    public void saveSelectedDish(SelectedDish selectedDish) {
        SelectedDishesEntity entity = new SelectedDishesEntity();
        entity.setDishId(selectedDish.getDishId());
        entity.setUserId(selectedDish.getUserId());
        entity.setQuantity(selectedDish.getQuantity());
        entity.setPrice(selectedDish.getPrice());
        selectedDishesRepository.save(entity);
    }
}
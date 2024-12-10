package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.domain.spi.SelectedDishPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.SelectedDishesEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.SelectedDishEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.SelectedDishesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SelectedDishJpaAdapter implements SelectedDishPersistencePort {

    private final SelectedDishesRepository selectedDishesRepository;
    private final SelectedDishEntityMapper selectedDishEntityMapper;

    @Override
    public void saveSelectedDish(SelectedDish selectedDish) {
        SelectedDishesEntity entity = selectedDishEntityMapper.toEntity(selectedDish);
        selectedDishesRepository.save(entity);
    }
}
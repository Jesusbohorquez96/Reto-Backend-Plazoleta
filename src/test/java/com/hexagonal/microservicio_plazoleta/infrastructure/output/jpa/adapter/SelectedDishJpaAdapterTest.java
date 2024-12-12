package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.SelectedDishesEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.SelectedDishEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.SelectedDishesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SelectedDishJpaAdapterTest {

    private SelectedDishesRepository selectedDishesRepository;
    private SelectedDishEntityMapper selectedDishEntityMapper;
    private SelectedDishJpaAdapter selectedDishJpaAdapter;

    @BeforeEach
    void setUp() {
        selectedDishesRepository = mock(SelectedDishesRepository.class);
        selectedDishEntityMapper = mock(SelectedDishEntityMapper.class);
        selectedDishJpaAdapter = new SelectedDishJpaAdapter(selectedDishesRepository, selectedDishEntityMapper);
    }

    @Test
    void saveSelectedDish_shouldMapAndSaveEntity() {
        SelectedDish selectedDish = new SelectedDish(1L, 2L, 3, 4L);
        SelectedDishesEntity entity = new SelectedDishesEntity();

        when(selectedDishEntityMapper.toEntity(selectedDish)).thenReturn(entity);

        selectedDishJpaAdapter.saveSelectedDish(selectedDish);

        verify(selectedDishEntityMapper, times(1)).toEntity(selectedDish);
        verify(selectedDishesRepository, times(1)).save(entity);
    }
}

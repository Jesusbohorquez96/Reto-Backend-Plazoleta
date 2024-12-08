package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.DishesEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.DishesEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IDishesRepository;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishesJpaAdapterTest {

    private IRestaurantRepository restaurantRepository;
    private IDishesRepository dishesRepository;
    private DishesEntityMapper dishesEntityMapper;
    private DishesJpaAdapter dishesJpaAdapter;

    @BeforeEach
    void setUp() {
        restaurantRepository = mock(IRestaurantRepository.class);
        dishesRepository = mock(IDishesRepository.class);
        dishesEntityMapper = mock(DishesEntityMapper.class);
        dishesJpaAdapter = new DishesJpaAdapter(restaurantRepository, dishesRepository, dishesEntityMapper);
    }

    @Test
    void shouldSaveDishesSuccessfully() {
        Dishes dishes = new Dishes();
        DishesEntity dishesEntity = new DishesEntity();

        when(dishesEntityMapper.toEntity(dishes)).thenReturn(dishesEntity);
        when(dishesRepository.save(dishesEntity)).thenReturn(dishesEntity);
        when(dishesEntityMapper.toDishes(dishesEntity)).thenReturn(dishes);

        dishesJpaAdapter.saveDishes(dishes);

        verify(dishesEntityMapper, times(1)).toEntity(dishes);
        verify(dishesRepository, times(1)).save(dishesEntity);
        verify(dishesEntityMapper, times(1)).toDishes(dishesEntity);
    }

    @Test
    void shouldReturnTrueIfRestaurantOwnedByUser() {
        Long restaurantId = 1L;
        Long ownerId = 2L;

        when(restaurantRepository.existsByIdAndOwnerId(restaurantId, ownerId)).thenReturn(true);

        boolean result = dishesJpaAdapter.isRestaurantOwnedByUser(restaurantId, ownerId);

        assertTrue(result);
        verify(restaurantRepository, times(1)).existsByIdAndOwnerId(restaurantId, ownerId);
    }

    @Test
    void shouldReturnFalseIfRestaurantNotOwnedByUser() {
        Long restaurantId = 1L;
        Long ownerId = 2L;

        when(restaurantRepository.existsByIdAndOwnerId(restaurantId, ownerId)).thenReturn(false);

        boolean result = dishesJpaAdapter.isRestaurantOwnedByUser(restaurantId, ownerId);

        assertFalse(result);
        verify(restaurantRepository, times(1)).existsByIdAndOwnerId(restaurantId, ownerId);
    }

    @Test
    void shouldFindByIdAndReturnDishes() {
        Long dishId = 1L;
        DishesEntity dishesEntity = new DishesEntity();
        Dishes dishes = new Dishes();

        when(dishesRepository.findByIdWithOwner(dishId)).thenReturn(Optional.of(dishesEntity));
        when(dishesEntityMapper.toDishes(dishesEntity)).thenReturn(dishes);

        Optional<Dishes> result = dishesJpaAdapter.findById(dishId);

        assertTrue(result.isPresent());
        assertEquals(dishes, result.get());
        verify(dishesRepository, times(1)).findByIdWithOwner(dishId);
        verify(dishesEntityMapper, times(1)).toDishes(dishesEntity);
    }

    @Test
    void shouldReturnEmptyOptionalIfDishNotFound() {
        Long dishId = 1L;

        when(dishesRepository.findByIdWithOwner(dishId)).thenReturn(Optional.empty());

        Optional<Dishes> result = dishesJpaAdapter.findById(dishId);

        assertFalse(result.isPresent());
        verify(dishesRepository, times(1)).findByIdWithOwner(dishId);
        verify(dishesEntityMapper, times(0)).toDishes(any(DishesEntity.class));
    }

    @Test
    void shouldUpdateDishStatusSuccessfully() {
        Long dishId = 1L;
        Boolean active = true;

        when(dishesRepository.existsById(dishId)).thenReturn(true);

        dishesJpaAdapter.updateDishStatus(dishId, active);

        verify(dishesRepository, times(1)).existsById(dishId);
        verify(dishesRepository, times(1)).updateActiveStatus(dishId, active);
    }

    @Test
    void shouldThrowExceptionIfDishNotFoundWhenUpdatingStatus() {
        Long dishId = 1L;
        Boolean active = true;

        when(dishesRepository.existsById(dishId)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> dishesJpaAdapter.updateDishStatus(dishId, active));

        assertEquals("Dish not found", exception.getMessage());
        verify(dishesRepository, times(1)).existsById(dishId);
        verify(dishesRepository, times(0)).updateActiveStatus(anyLong(), anyBoolean());
    }
}

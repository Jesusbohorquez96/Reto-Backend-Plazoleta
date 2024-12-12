package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.DishesEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.DishesEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IDishesRepository;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.DISH_NOT_FOUNT;
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
    void saveDishes_shouldSaveAndMapEntity() {
        Dishes dishes = new Dishes(1L, "Dish 1", 100.0, "Description", "url", "Category", 1L, true, 1L);
        DishesEntity dishesEntity = new DishesEntity();
        when(dishesEntityMapper.toEntity(dishes)).thenReturn(dishesEntity);
        when(dishesRepository.save(dishesEntity)).thenReturn(dishesEntity);
        when(dishesEntityMapper.toDishes(dishesEntity)).thenReturn(dishes);

        dishesJpaAdapter.saveDishes(dishes);

        verify(dishesRepository, times(1)).save(dishesEntity);
        verify(dishesEntityMapper, times(1)).toDishes(dishesEntity);
    }

    @Test
    void isRestaurantOwnedByUser_shouldReturnTrueIfOwned() {
        when(restaurantRepository.existsByIdAndOwnerId(1L, 1L)).thenReturn(true);

        boolean result = dishesJpaAdapter.isRestaurantOwnedByUser(1L, 1L);

        assertTrue(result);
        verify(restaurantRepository, times(1)).existsByIdAndOwnerId(1L, 1L);
    }

    @Test
    void findById_shouldReturnOptionalMappedDishes() {
        DishesEntity dishesEntity = new DishesEntity();
        Dishes dishes = new Dishes();
        when(dishesRepository.findByIdWithOwner(1L)).thenReturn(Optional.of(dishesEntity));
        when(dishesEntityMapper.toDishes(dishesEntity)).thenReturn(dishes);

        Optional<Dishes> result = dishesJpaAdapter.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(dishes, result.get());
        verify(dishesRepository, times(1)).findByIdWithOwner(1L);
    }

    @Test
    void updateDishStatus_shouldUpdateIfExists() {
        when(dishesRepository.existsById(1L)).thenReturn(true);

        dishesJpaAdapter.updateDishStatus(1L, true);

        verify(dishesRepository, times(1)).updateActiveStatus(1L, true);
    }

    @Test
    void updateDishStatus_shouldThrowExceptionIfNotExists() {
        when(dishesRepository.existsById(1L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                dishesJpaAdapter.updateDishStatus(1L, true)
        );

        assertEquals(DISH_NOT_FOUNT, exception.getMessage());
        verify(dishesRepository, never()).updateActiveStatus(anyLong(), anyBoolean());
    }

    @Test
    void existsByNameAndRestaurantId_shouldReturnValue() {
        when(dishesRepository.existsByNameAndRestaurantId("Dish 1", 1L)).thenReturn(true);

        boolean result = dishesJpaAdapter.existsByNameAndRestaurantId("Dish 1", 1L);

        assertTrue(result);
        verify(dishesRepository, times(1)).existsByNameAndRestaurantId("Dish 1", 1L);
    }

   // Test for the function getDishById

    @Test
    void getDishById_shouldThrowExceptionIfNotFound() {
        when(dishesRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                dishesJpaAdapter.getDishById(1L)
        );

        assertEquals(DISH_NOT_FOUNT, exception.getMessage());
        verify(dishesRepository, times(1)).findById(1L);
    }
}

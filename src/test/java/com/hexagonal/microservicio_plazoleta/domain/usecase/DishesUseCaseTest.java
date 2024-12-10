package com.hexagonal.microservicio_plazoleta.domain.usecase;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishesUseCaseTest {

    private IDishesPersistencePort dishesPersistencePort;
    private DishesUseCase dishesUseCase;

    @BeforeEach
    void setUp() {
        dishesPersistencePort = mock(IDishesPersistencePort.class);
        dishesUseCase = new DishesUseCase(dishesPersistencePort);
    }

    @Test
    void shouldSaveDishesSuccessfullyWhenUserIsOwner() {
        Dishes dishes = new Dishes();
        dishes.setRestaurantId(1L);
        dishes.setOwnerId(2L);

        when(dishesPersistencePort.isRestaurantOwnedByUser(1L, 2L)).thenReturn(true);

        dishesUseCase.saveDishes(dishes);

        verify(dishesPersistencePort, times(1)).saveDishes(dishes);
    }

    @Test
    void shouldThrowUnauthorizedExceptionWhenUserIsNotOwner() {
        Dishes dishes = new Dishes();
        dishes.setRestaurantId(1L);
        dishes.setOwnerId(2L);

        when(dishesPersistencePort.isRestaurantOwnedByUser(1L, 2L)).thenReturn(false);

        UnauthorizedException exception = assertThrows(UnauthorizedException.class,
                () -> dishesUseCase.saveDishes(dishes));

        assertEquals(USER_NOT_OWNER_RESTAURANT, exception.getMessage());
        verify(dishesPersistencePort, times(0)).saveDishes(any(Dishes.class));
    }

    @Test
    void shouldUpdateDishSuccessfully() {
        Long dishId = 1L;
        Double newPrice = 123.45;
        String newDescription = "Updated description";
        Long ownerId = 2L;

        Dishes existingDish = new Dishes();
        existingDish.setId(dishId);
        existingDish.setOwnerId(ownerId);

        when(dishesPersistencePort.findById(dishId)).thenReturn(Optional.of(existingDish));

        dishesUseCase.updateDish(dishId, newPrice, newDescription, ownerId);

        assertEquals(newPrice, existingDish.getPrice());
        assertEquals(newDescription, existingDish.getDescription());
        verify(dishesPersistencePort, times(1)).saveDishes(existingDish);
    }

    @Test
    void shouldThrowUnauthorizedExceptionWhenUpdatingDishWithDifferentOwner() {
        Long dishId = 1L;
        Double newPrice = 123.45;
        String newDescription = "Updated description";
        Long ownerId = 2L;

        Dishes existingDish = new Dishes();
        existingDish.setId(dishId);
        existingDish.setOwnerId(3L); // Different owner

        when(dishesPersistencePort.findById(dishId)).thenReturn(Optional.of(existingDish));

        UnauthorizedException exception = assertThrows(UnauthorizedException.class,
                () -> dishesUseCase.updateDish(dishId, newPrice, newDescription, ownerId));

        assertEquals(OWNER_NOT_DISH, exception.getMessage());
        verify(dishesPersistencePort, times(0)).saveDishes(any(Dishes.class));
    }

    @Test
    void shouldThrowExceptionWhenDishNotFoundForUpdate() {
        Long dishId = 1L;
        Double newPrice = 123.45;
        String newDescription = "Updated description";
        Long ownerId = 2L;

        when(dishesPersistencePort.findById(dishId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> dishesUseCase.updateDish(dishId, newPrice, newDescription, ownerId));

        assertEquals(DISH_NOT_FOUND, exception.getMessage());
        verify(dishesPersistencePort, times(0)).saveDishes(any(Dishes.class));
    }

    @Test
    void shouldUpdateDishStatusSuccessfully() {
        Long dishId = 1L;
        Boolean newStatus = true;
        Long ownerId = 2L;

        Dishes existingDish = new Dishes();
        existingDish.setId(dishId);
        existingDish.setOwnerId(ownerId);

        when(dishesPersistencePort.findById(dishId)).thenReturn(Optional.of(existingDish));

        dishesUseCase.updateDishStatus(dishId, newStatus, ownerId);

        verify(dishesPersistencePort, times(1)).updateDishStatus(dishId, newStatus);
    }

    @Test
    void shouldThrowUnauthorizedExceptionWhenUpdatingDishStatusWithDifferentOwner() {
        Long dishId = 1L;
        Boolean newStatus = true;
        Long ownerId = 2L;

        Dishes existingDish = new Dishes();
        existingDish.setId(dishId);
        existingDish.setOwnerId(3L); // Different owner

        when(dishesPersistencePort.findById(dishId)).thenReturn(Optional.of(existingDish));

        UnauthorizedException exception = assertThrows(UnauthorizedException.class,
                () -> dishesUseCase.updateDishStatus(dishId, newStatus, ownerId));

        assertEquals("You are not the owner of this dish.", exception.getMessage());
        verify(dishesPersistencePort, times(0)).updateDishStatus(anyLong(), anyBoolean());
    }
}

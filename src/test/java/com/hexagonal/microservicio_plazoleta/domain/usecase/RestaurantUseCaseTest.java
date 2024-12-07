package com.hexagonal.microservicio_plazoleta.domain.usecase;

import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import com.hexagonal.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    private IRestaurantPersistencePort restaurantPersistencePort;
    private RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort) {};
    }

    @Test
    void shouldSaveRestaurantSuccessfully() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Maravilla");
        restaurant.setNit(123456789);

        when(restaurantPersistencePort.existsByName(anyString())).thenReturn(false);
        when(restaurantPersistencePort.existsByNit(anyInt())).thenReturn(false);

        restaurantUseCase.saveRestaurant(restaurant);

        verify(restaurantPersistencePort, times(1)).saveRestaurant(restaurant);
    }

    @Test
    void shouldThrowExceptionWhenNameIsNotUnique() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Maravilla");
        restaurant.setNit(123456789);

        when(restaurantPersistencePort.existsByName("Maravilla")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.saveRestaurant(restaurant), UNIQUE_NAME);

        verify(restaurantPersistencePort, times(0)).saveRestaurant(any(Restaurant.class));
    }

    @Test
    void shouldThrowExceptionWhenNitIsNotUnique() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Maravilla");
        restaurant.setNit(123456789);

        when(restaurantPersistencePort.existsByName("Maravilla")).thenReturn(false);
        when(restaurantPersistencePort.existsByNit(123456789)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.saveRestaurant(restaurant), NIT_NAME);

        verify(restaurantPersistencePort, times(0)).saveRestaurant(any(Restaurant.class));
    }
}

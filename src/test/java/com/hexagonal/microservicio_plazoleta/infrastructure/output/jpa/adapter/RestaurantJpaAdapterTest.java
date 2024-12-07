package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantJpaAdapterTest {

    private RestaurantJpaAdapter restaurantJpaAdapter;

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private RestaurantEntityMapper restaurantEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantJpaAdapter = new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Test
    void shouldSaveRestaurantSuccessfully() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Maravilla");
        restaurant.setNit(123456789);
        restaurant.setAddress("Calle 23 #23-34");
        restaurant.setPhone("+573005698325");
        restaurant.setUrlLogo("https://example.com/logo.png");

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setName("Maravilla");
        restaurantEntity.setNit(123456789);
        restaurantEntity.setAddress("Calle 23 #23-34");
        restaurantEntity.setPhone("+573005698325");
        restaurantEntity.setUrlLogo("https://example.com/logo.png");

        when(restaurantEntityMapper.toEntity(any(Restaurant.class))).thenReturn(restaurantEntity);
        when(restaurantRepository.save(any(RestaurantEntity.class))).thenReturn(restaurantEntity);
        when(restaurantEntityMapper.toRestaurant(any(RestaurantEntity.class))).thenReturn(restaurant);

        Restaurant savedRestaurant = restaurantJpaAdapter.saveRestaurant(restaurant);

        assertNotNull(savedRestaurant);
        assertEquals("Maravilla", savedRestaurant.getName());
        verify(restaurantEntityMapper, times(1)).toEntity(restaurant);
        verify(restaurantRepository, times(1)).save(restaurantEntity);
        verify(restaurantEntityMapper, times(1)).toRestaurant(restaurantEntity);
    }

    @Test
    void shouldReturnTrueIfRestaurantNameExists() {
        when(restaurantRepository.existsByName("Maravilla")).thenReturn(true);

        boolean exists = restaurantJpaAdapter.existsByName("Maravilla");

        assertTrue(exists);
        verify(restaurantRepository, times(1)).existsByName("Maravilla");
    }

    @Test
    void shouldReturnFalseIfRestaurantNameDoesNotExist() {
        when(restaurantRepository.existsByName("Maravilla")).thenReturn(false);

        boolean exists = restaurantJpaAdapter.existsByName("Maravilla");

        assertFalse(exists);
        verify(restaurantRepository, times(1)).existsByName("Maravilla");
    }

    @Test
    void shouldReturnTrueIfRestaurantNitExists() {
        when(restaurantRepository.existsByNit(123456789)).thenReturn(true);

        boolean exists = restaurantJpaAdapter.existsByNit(123456789);

        assertTrue(exists);
        verify(restaurantRepository, times(1)).existsByNit(123456789);
    }

    @Test
    void shouldReturnFalseIfRestaurantNitDoesNotExist() {
        when(restaurantRepository.existsByNit(123456789)).thenReturn(false);

        boolean exists = restaurantJpaAdapter.existsByNit(123456789);

        assertFalse(exists);
        verify(restaurantRepository, times(1)).existsByNit(123456789);
    }
}

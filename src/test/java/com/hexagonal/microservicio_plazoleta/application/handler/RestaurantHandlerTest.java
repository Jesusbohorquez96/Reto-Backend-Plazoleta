package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;
import com.hexagonal.microservicio_plazoleta.application.mapper.RestaurantRequestMapper;
import com.hexagonal.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantHandlerTest {

    private RestaurantHandler restaurantHandler;

    @Mock
    private RestaurantRequestMapper restaurantRequestMapper;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantHandler = new RestaurantHandler(restaurantRequestMapper, restaurantServicePort);
    }

    @Test
    void shouldSaveRestaurantSuccessfully() {
        RestaurantRequest restaurantRequest = new RestaurantRequest();
        restaurantRequest.setName("Maravilla");
        restaurantRequest.setNit(123456789);
        restaurantRequest.setAddress("Calle 23 #23-34");
        restaurantRequest.setPhone("+573005698325");
        restaurantRequest.setUrlLogo("https://example.com/logo.png");

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Maravilla");
        restaurant.setNit(123456789);
        restaurant.setAddress("Calle 23 #23-34");
        restaurant.setPhone("+573005698325");
        restaurant.setUrlLogo("https://example.com/logo.png");

        when(restaurantRequestMapper.toRestaurant(any(RestaurantRequest.class))).thenReturn(restaurant);

        restaurantHandler.saveRestaurant(restaurantRequest);

        verify(restaurantRequestMapper, times(1)).toRestaurant(restaurantRequest);
        verify(restaurantServicePort, times(1)).saveRestaurant(restaurant);
    }
}

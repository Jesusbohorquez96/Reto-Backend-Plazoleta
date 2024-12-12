package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.ListRestaurantResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;
import com.hexagonal.microservicio_plazoleta.application.mapper.RestaurantRequestMapper;
import com.hexagonal.microservicio_plazoleta.application.mapper.RestaurantResponseMapper;
import com.hexagonal.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantHandlerTest {

    private RestaurantRequestMapper restaurantRequestMapper;
    private IRestaurantServicePort restaurantServicePort;
    private RestaurantResponseMapper restaurantResponseMapper;
    private RestaurantHandler restaurantHandler;

    @BeforeEach
    void setUp() {
        restaurantRequestMapper = mock(RestaurantRequestMapper.class);
        restaurantServicePort = mock(IRestaurantServicePort.class);
        restaurantResponseMapper = mock(RestaurantResponseMapper.class);
        restaurantHandler = new RestaurantHandler(restaurantRequestMapper, restaurantServicePort, restaurantResponseMapper);
    }

    @Test
    void saveRestaurant_shouldCallMapperAndService() {
        RestaurantRequest restaurantRequest = new RestaurantRequest("Restaurant A", "123456", "Address", "Owner");
        Restaurant restaurant = new Restaurant(1L, "Restaurant A", "123456", "Address", "Owner");

        when(restaurantRequestMapper.toRestaurant(restaurantRequest)).thenReturn(restaurant);

        restaurantHandler.saveRestaurant(restaurantRequest);

        verify(restaurantRequestMapper, times(1)).toRestaurant(restaurantRequest);
        verify(restaurantServicePort, times(1)).saveRestaurant(restaurant);
    }

    @Test
    void listRestaurants_shouldReturnPagedData() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        ListRestaurantResponse response = new ListRestaurantResponse(1L, "Restaurant A", "url");
        Page<ListRestaurantResponse> pagedResponse = new PageImpl<>(List.of(response));

        when(restaurantServicePort.listRestaurants(pageRequest)).thenReturn(pagedResponse);

        Page<ListRestaurantResponse> result = restaurantHandler.listRestaurants(pageRequest);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(response, result.getContent().get(0));
        verify(restaurantServicePort, times(1)).listRestaurants(pageRequest);
    }


}

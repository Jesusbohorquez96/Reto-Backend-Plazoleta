package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.DishStatusUpdateRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.DishesRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.DishesUpdateRequest;
import com.hexagonal.microservicio_plazoleta.application.mapper.DishesRequestMapper;
import com.hexagonal.microservicio_plazoleta.domain.api.IDishesServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;

class DishesHandlerTest {

    private DishesRequestMapper dishesRequestMapper;
    private IDishesServicePort dishesServicePort;
    private DishesHandler dishesHandler;

    @BeforeEach
    void setUp() {
        dishesRequestMapper = mock(DishesRequestMapper.class);
        dishesServicePort = mock(IDishesServicePort.class);
        dishesHandler = new DishesHandler(dishesRequestMapper, dishesServicePort);

        // Mock SecurityContextHolder
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("1"); // Mock user ID as "1"
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void shouldSaveDishesSuccessfully() {
        DishesRequest dishesRequest = new DishesRequest();
        Dishes dishes = new Dishes();

        when(dishesRequestMapper.toDishes(dishesRequest, 1L)).thenReturn(dishes);

        dishesHandler.saveDishes(dishesRequest);

        verify(dishesRequestMapper, times(1)).toDishes(dishesRequest, 1L);
        verify(dishesServicePort, times(1)).saveDishes(dishes);
    }

    @Test
    void shouldUpdateDishSuccessfully() {
        Long dishId = 1L;
        DishesUpdateRequest updateRequest = new DishesUpdateRequest();
        updateRequest.setPrice(20.00);
        updateRequest.setDescription("Updated description");

        dishesHandler.updateDish(dishId, updateRequest);

        verify(dishesServicePort, times(1)).updateDish(dishId, 123.45, "Updated description", 1L);
    }

    @Test
    void shouldUpdateDishStatusSuccessfully() {
        Long dishId = 1L;
        DishStatusUpdateRequest statusUpdateRequest = new DishStatusUpdateRequest();
        statusUpdateRequest.setActive(true);

        dishesHandler.updateDishStatus(dishId, statusUpdateRequest);

        verify(dishesServicePort, times(1)).updateDishStatus(dishId, true, 1L);
    }
}

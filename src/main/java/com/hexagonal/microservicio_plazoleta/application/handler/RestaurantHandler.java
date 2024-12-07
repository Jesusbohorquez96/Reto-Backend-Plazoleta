package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;
import com.hexagonal.microservicio_plazoleta.application.mapper.RestaurantRequestMapper;
import com.hexagonal.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final RestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public void saveRestaurant(RestaurantRequest restaurantRequest) {
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequest));
    }
}

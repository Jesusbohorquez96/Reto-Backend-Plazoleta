package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.IdRestaurantResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.ListRestaurantResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;
import com.hexagonal.microservicio_plazoleta.application.mapper.RestaurantRequestMapper;
import com.hexagonal.microservicio_plazoleta.application.mapper.RestaurantResponseMapper;
import com.hexagonal.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final RestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void saveRestaurant(RestaurantRequest restaurantRequest) {
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequest));
    }

    @Override
    public Page<ListRestaurantResponse> listRestaurants(Pageable pageable) {
        return restaurantServicePort.listRestaurants(pageable);
    }

    @Override
    public IdRestaurantResponse getRestaurantById(Long id) {
     Restaurant restaurant =  restaurantServicePort.getRestaurantById(id);
        return restaurantResponseMapper.toIdResponse( restaurant);
    }
}

package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequest restaurantRequest);
}

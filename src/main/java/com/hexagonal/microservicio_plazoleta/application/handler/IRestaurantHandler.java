package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.IdRestaurantResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.ListRestaurantResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;
import org.springframework.data.domain.*;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequest restaurantRequest);

    Page<ListRestaurantResponse> listRestaurants(Pageable pageable);

    IdRestaurantResponse getRestaurantById(Long id);
}

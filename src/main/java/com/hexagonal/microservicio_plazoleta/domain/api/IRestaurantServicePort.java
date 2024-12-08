package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.application.dto.ListRestaurantResponse;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.springframework.data.domain.*;

public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant restaurant);

    Page<ListRestaurantResponse> listRestaurants(Pageable pageable);
}

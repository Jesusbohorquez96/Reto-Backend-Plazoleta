package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant restaurant);
}

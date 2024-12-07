package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IRestaurantPersistencePort {

    boolean existsByName(String name);
    boolean existsByNit(Integer nit);
    Restaurant saveRestaurant(Restaurant restaurant);

}

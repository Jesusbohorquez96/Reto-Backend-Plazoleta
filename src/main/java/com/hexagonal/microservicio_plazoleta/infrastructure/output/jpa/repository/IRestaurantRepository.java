package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository;

import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    boolean existsByName(String name);
    boolean existsByNit(Integer nit);
    boolean existsByIdAndOwnerId(Long restaurantId, Long ownerId);
    Page<RestaurantEntity> findAll(Pageable pageable);
}

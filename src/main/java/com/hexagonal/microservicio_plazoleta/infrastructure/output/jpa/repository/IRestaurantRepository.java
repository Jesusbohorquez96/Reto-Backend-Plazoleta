package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository;

import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    boolean existsByName(String name);
    boolean existsByNit(Integer nit);
    Optional<Object> findByName(String name);
}

package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository;

import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.DishesEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IDishesRepository extends JpaRepository<DishesEntity, Long> {

    @Query("SELECT d FROM DishesEntity d JOIN d.restaurant r WHERE d.id = :dishId")
    Optional<DishesEntity> findByIdWithOwner(@Param("dishId") Long dishId);
}

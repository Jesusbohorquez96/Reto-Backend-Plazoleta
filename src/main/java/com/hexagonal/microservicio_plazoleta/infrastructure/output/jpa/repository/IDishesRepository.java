package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository;

import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.DishesEntity;
import feign.Param;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface IDishesRepository extends JpaRepository<DishesEntity, Long> {

    @Query("SELECT d FROM DishesEntity d JOIN d.restaurant r WHERE d.id = :dishId")
    Optional<DishesEntity> findByIdWithOwner(@Param("dishId") Long dishId);

    @Modifying
    @Query("UPDATE DishesEntity d SET d.active = :active WHERE d.id = :dishId")
    void updateActiveStatus(@Param("dishId") Long dishId, @Param("active") Boolean active);

    Page<DishesEntity> findByRestaurantId(Long restaurantId, Pageable pageable);

    Page<DishesEntity> findByRestaurantIdAndCategory(Long restaurantId, String category, Pageable pageable);

    boolean existsByNameAndRestaurantId(String name, Long restaurantId);
}

package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import org.springframework.data.domain.*;
import java.util.Optional;

public interface IDishesPersistencePort {

    void saveDishes(Dishes dishes);

    boolean isRestaurantOwnedByUser(Long restaurantId, Long ownerId);

    Optional<Dishes> findById(Long id);

    void updateDishStatus(Long dishId, Boolean active);

    Page<Dishes> findByRestaurantId(Long restaurantId, Pageable pageable);

    Page<Dishes> findByRestaurantIdAndCategory(Long restaurantId, String category, Pageable pageable);

    boolean existsByNameAndRestaurantId(String name, Long restaurantId);
}

package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.DishesEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.DishesEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IDishesRepository;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Component
public class DishesJpaAdapter implements IDishesPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IDishesRepository dishesRepository;
    private final DishesEntityMapper dishesEntityMapper;

    @Override
    public void saveDishes(Dishes dishes) {
        DishesEntity dishesEntity = dishesRepository.save(dishesEntityMapper.toEntity(dishes));
        dishesEntityMapper.toDishes(dishesEntity);
    }

    @Override
    public boolean isRestaurantOwnedByUser(Long restaurantId, Long ownerId) {
        return restaurantRepository.existsByIdAndOwnerId(restaurantId, ownerId);
    }

    @Override
    public Optional<Dishes> findById(Long id) {
        return dishesRepository.findByIdWithOwner(id).map(dishesEntityMapper::toDishes);
    }

    @Override
    @Transactional
    public void updateDishStatus(Long dishId, Boolean active) {
        if (dishesRepository.existsById(dishId)) {
            dishesRepository.updateActiveStatus(dishId, active);
        } else {
            throw new IllegalArgumentException("Dish not found");
        }
    }

    @Override
    public boolean existsByNameAndRestaurantId(String name, Long restaurantId) {
        return dishesRepository.existsByNameAndRestaurantId(name, restaurantId);
    }

    @Override
    public Page<Dishes> findByRestaurantId(Long restaurantId, Pageable pageable) {
        return dishesRepository.findByRestaurantId(restaurantId, pageable)
                .map(dishesEntityMapper::toDishes);
    }

    @Override
    public Page<Dishes> findByRestaurantIdAndCategory(Long restaurantId, String category, Pageable pageable) {
        return dishesRepository.findByRestaurantIdAndCategory(restaurantId, category, pageable)
                .map(dishesEntityMapper::toDishes);
    }

    @Override
    public Dishes getDishById(Long dishId) {
        DishesEntity entity = dishesRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        return new Dishes(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getCategory(),
                entity.getRestaurant().getId(),
                entity.isActive(),
                entity.getRestaurant().getOwnerId()
        );
    }
}

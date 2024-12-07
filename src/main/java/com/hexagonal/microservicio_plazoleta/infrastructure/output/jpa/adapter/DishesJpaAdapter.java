package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.DishesEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.DishesEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IDishesRepository;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
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

}

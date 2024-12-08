package com.hexagonal.microservicio_plazoleta.domain.usecase;

import com.hexagonal.microservicio_plazoleta.domain.api.IDishesServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.exception.UnauthorizedException;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

public class DishesUseCase implements IDishesServicePort {

    private final IDishesPersistencePort dishesPersistencePort;

    public DishesUseCase(IDishesPersistencePort dishesPersistencePort) {
        this.dishesPersistencePort = dishesPersistencePort;
    }

    @Override
    public void saveDishes(Dishes dishes) {
        boolean isOwner = dishesPersistencePort.isRestaurantOwnedByUser(dishes.getRestaurantId(), dishes.getOwnerId());
        if (!isOwner) {
            throw new UnauthorizedException(USER_NOT_OWNER_RESTAURANT);
        }
        dishesPersistencePort.saveDishes(dishes);
    }

    @Override
    public void updateDish(Long dishId, Integer price, String description, Long ownerId) {
        Dishes dish = dishesPersistencePort.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException(DISH_NOT_FOUND));
        if (!dish.getOwnerId().equals(ownerId)) {
            throw new UnauthorizedException(OWNER_NOT_DISH);
        }
        if (price != null) {
            dish.setPrice(price);
        }
        if (description != null) {
            dish.setDescription(description);
        }
        dishesPersistencePort.saveDishes(dish);
    }

    @Override
    public void updateDishStatus(Long dishId, Boolean active, Long ownerId) {
        Dishes dish = dishesPersistencePort.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException(DISH_NOT_FOUND));
        if (!dish.getOwnerId().equals(ownerId)) {
            throw new UnauthorizedException(OWNER_NOT_DISH);
        }
        dishesPersistencePort.updateDishStatus(dishId, active);
    }
}

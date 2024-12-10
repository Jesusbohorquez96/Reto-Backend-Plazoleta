package com.hexagonal.microservicio_plazoleta.infrastructure.utils;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;

import java.util.List;
import java.util.stream.Collectors;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

public class SelectedValidator {

    public static Double calculateTotalPrice(Double price, Integer quantity) {
        if (price == null || price <= ZERO) {
            throw new IllegalArgumentException(DISH_PRICE_ZERO);
        }
        if (quantity == null || quantity <= ZERO) {
            throw new IllegalArgumentException(QUANTITY_ZERO);
        }
        return price * quantity;
    }

    public static void validateSameRestaurant(List<Dishes> dishes) {
        Long restaurantId = dishes.get(ZERO).getRestaurantId();
        List<Long> invalidDishIds = dishes.stream()
                .filter(dish -> !dish.getRestaurantId().equals(restaurantId))
                .map(Dishes::getId)
                .collect(Collectors.toList());

        if (!invalidDishIds.isEmpty()) {
            throw new IllegalArgumentException(
                    SELECTED_DISHES_SAME_RESTAURANT + invalidDishIds
            );
        }
    }
}

package com.hexagonal.microservicio_plazoleta.domain.Utils;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SelectedValidator {

    public static void validateDishIsActive(Dishes dish) {
        if (!dish.isActive()) {
            throw new IllegalArgumentException("The dish is not active and cannot be selected.");
        }
    }

    public static Double calculateTotalPrice(Double price, Integer quantity) {
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Dish price must be greater than zero.");
        }
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        return price * quantity;
    }

    public static void validateSameRestaurant(List<Dishes> dishes) {
        if (dishes == null || dishes.isEmpty()) {
            return;
        }
        Set<Long> restaurantIds = dishes.stream()
                .map(Dishes::getRestaurantId)
                .collect(Collectors.toSet());

        if (restaurantIds.size() > 1) {
            throw new IllegalArgumentException("All selected dishes must belong to the same restaurant.");
        }
    }
}

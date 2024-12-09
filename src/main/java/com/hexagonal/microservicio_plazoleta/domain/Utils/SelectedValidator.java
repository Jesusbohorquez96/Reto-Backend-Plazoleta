package com.hexagonal.microservicio_plazoleta.domain.Utils;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;

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
}

package com.hexagonal.microservicio_plazoleta.infrastructure.exception;

public class DishValidationException extends RuntimeException{

    private final Long dishId;

    public DishValidationException(String message, Long dishId) {
        super(message);
        this.dishId = dishId;
    }

    public Long getDishId() {
        return dishId;
    }
}
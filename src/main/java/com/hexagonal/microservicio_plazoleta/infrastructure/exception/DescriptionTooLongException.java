package com.hexagonal.microservicio_plazoleta.infrastructure.exception;

public class DescriptionTooLongException extends RuntimeException {
    public DescriptionTooLongException(String descriptionIsTooLong) {
        super();
    }
}

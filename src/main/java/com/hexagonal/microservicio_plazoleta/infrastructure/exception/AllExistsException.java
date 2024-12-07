package com.hexagonal.microservicio_plazoleta.infrastructure.exception;

public class AllExistsException extends RuntimeException {
    public AllExistsException(String massage) {
        super(massage);
    }
}

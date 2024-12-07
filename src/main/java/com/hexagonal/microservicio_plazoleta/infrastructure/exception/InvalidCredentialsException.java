package com.hexagonal.microservicio_plazoleta.infrastructure.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}

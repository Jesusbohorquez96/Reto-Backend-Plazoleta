package com.hexagonal.microservicio_plazoleta.infrastructure.exception;

public class NameTooLongException extends RuntimeException {
    public NameTooLongException(String nameIsTooLong) {
        super();
    }
}

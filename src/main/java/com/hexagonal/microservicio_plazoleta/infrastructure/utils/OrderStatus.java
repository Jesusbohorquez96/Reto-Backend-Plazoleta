package com.hexagonal.microservicio_plazoleta.infrastructure.utils;

public enum OrderStatus {

    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    READY("READY"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

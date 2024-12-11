package com.hexagonal.microservicio_plazoleta.infrastructure.utils;

public enum OrderStatus {

    PENDING("PENDING"),
    IN_PROGRESS("En proceso"),
    DELIVERED("Entregado"),
    CANCELED("Cancelado");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

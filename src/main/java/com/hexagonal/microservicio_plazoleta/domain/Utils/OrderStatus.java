package com.hexagonal.microservicio_plazoleta.domain.Utils;

public enum OrderStatus {

    PENDING("Pendiente"),
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

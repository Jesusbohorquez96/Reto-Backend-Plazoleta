package com.hexagonal.microservicio_plazoleta.application.dto;

public class IdRestaurantResponse {

    private Long restaurantId;
    private Long ownerId;


    public IdRestaurantResponse(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}

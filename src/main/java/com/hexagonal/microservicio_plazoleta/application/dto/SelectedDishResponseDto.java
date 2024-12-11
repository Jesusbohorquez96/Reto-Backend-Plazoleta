package com.hexagonal.microservicio_plazoleta.application.dto;

public class SelectedDishResponseDto {

    private Long dishId;
    private int quantity;
    private Double price;

    public SelectedDishResponseDto(Long dishId, int quantity, Double price) {
        this.dishId = dishId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

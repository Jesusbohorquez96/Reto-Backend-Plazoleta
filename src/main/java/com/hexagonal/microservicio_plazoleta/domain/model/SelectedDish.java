package com.hexagonal.microservicio_plazoleta.domain.model;


public class SelectedDish {

    private Long id;
    private Long dishId;
    private Long userId;
    private Integer quantity;
    private Double price;

    public SelectedDish(Long id, Long dishId, Long userId, Integer quantity, Double price) {
        this.id = id;
        this.dishId = dishId;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
    }

    public SelectedDish() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
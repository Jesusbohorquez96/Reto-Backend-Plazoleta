package com.hexagonal.microservicio_plazoleta.domain.model;

public class SelectedDish {

    private Long id;
    private Long dishId;
    private Long userId;
    private Integer quantity;
    private Double price;
    private Order order;

    public SelectedDish(Long id, Long dishId, Long userId, Integer quantity, Double price, Order order) {
        this.id = id;
        this.dishId = dishId;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    public SelectedDish() {
    }

    public SelectedDish(Object o, Long id, Integer quantity, Double aDouble, javax.persistence.criteria.Order order) {
    }

    public SelectedDish(long l, long l1, int i, long l2) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderId) {
        this.order = orderId;
    }
}
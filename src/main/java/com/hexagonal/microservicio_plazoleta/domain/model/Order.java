package com.hexagonal.microservicio_plazoleta.domain.model;

import com.hexagonal.microservicio_plazoleta.domain.Utils.OrderStatus;

import java.util.List;

public class Order {

    private Long id;
    private Long restaurantId;
    private Long userId;
    private OrderStatus status;
    private List<SelectedDish> selectedDishes;

    public Order(Long id, Long restaurantId, Long userId, OrderStatus status, List<SelectedDish> selectedDishes) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.status = status;
        this.selectedDishes = selectedDishes;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<SelectedDish> getSelectedDishes() {
        return selectedDishes;
    }

    public void setSelectedDishes(List<SelectedDish> selectedDishes) {
        this.selectedDishes = selectedDishes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                ", status=" + status +
                ", selectedDishes=" + selectedDishes +
                '}';
    }
}

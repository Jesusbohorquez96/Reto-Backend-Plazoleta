package com.hexagonal.microservicio_plazoleta.application.dto;

import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import java.util.List;

public class OrderResponse {

    private Long id;
    private Long restaurantId;
    private Long userId;
    private OrderStatus status;
    private List<SelectedDishResponseDto> selectedDishes;

    public OrderResponse(Long id, Long restaurantId, Long userId, OrderStatus status, List<SelectedDishResponseDto> selectedDishes) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.status = status;
        this.selectedDishes = selectedDishes;
    }

    public OrderResponse() {

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

    public List<SelectedDishResponseDto> getSelectedDishes() {
        return selectedDishes;
    }

    public void setSelectedDishes(List<SelectedDishResponseDto> selectedDishes) {
        this.selectedDishes = selectedDishes;
    }
}

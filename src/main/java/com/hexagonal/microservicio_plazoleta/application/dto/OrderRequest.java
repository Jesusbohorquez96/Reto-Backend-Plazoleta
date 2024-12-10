package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {

    @NotNull(message = "Restaurant ID is required")
    private Long restaurantId;

    @NotNull(message = "Selected dishes are required")
    private List<SelectedDishesRequest> selectedDishes;
}
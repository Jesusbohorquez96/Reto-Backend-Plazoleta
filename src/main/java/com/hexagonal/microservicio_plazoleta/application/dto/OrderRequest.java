package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Data
public class OrderRequest {

    @NotNull(message = RESTAURANT_REQUIRED)
    private Long restaurantId;

    @NotNull(message = SELECTED_DISHES_REQUIRED)
    private List<SelectedDishesRequest> selectedDishes;

    public OrderRequest(long l, List<Object> of) {
    }

    public OrderRequest() {

    }
}
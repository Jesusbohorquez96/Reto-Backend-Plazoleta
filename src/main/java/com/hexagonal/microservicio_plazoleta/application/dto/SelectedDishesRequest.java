package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Data
public class SelectedDishesRequest {

    @NotNull(message = DISH_REQUIRED)
    private Long dishId;

    @NotNull(message = QUANTITY_REQUIRED)
    private Integer quantity;

    public SelectedDishesRequest(long l, int i) {
    }
}

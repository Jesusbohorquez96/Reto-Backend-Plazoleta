package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.*;
import javax.validation.constraints.*;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Data
public class DishesUpdateRequest {

    @Positive(message = PRICE_POSITIVE)
    private Double price;

    @NotBlank(message = DESCRIPTION)
    private String description;

    public DishesUpdateRequest(double v, String updatedDescription) {
    }
}


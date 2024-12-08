package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.*;
import javax.validation.constraints.*;

@Data
public class DishesUpdateRequest {

    @Positive(message = "The price must be positive.")
    private Integer price;

    @NotBlank(message = "Description is required.")
    private String description;
}


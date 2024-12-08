package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DishStatusUpdateRequest {

    @NotNull(message = "The active status must not be null.")
    private Boolean active;
}

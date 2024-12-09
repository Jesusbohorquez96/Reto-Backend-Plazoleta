package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SelectedDishRequest {

    @NotNull(message = "El ID del plato es obligatorio")
    private Long dishId;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer quantity;
}

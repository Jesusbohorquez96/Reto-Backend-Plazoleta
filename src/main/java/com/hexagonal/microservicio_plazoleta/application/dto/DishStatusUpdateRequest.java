package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Data
public class DishStatusUpdateRequest {

    @NotNull(message = ACTIVE_STATUS)
    private Boolean active;
}

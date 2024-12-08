package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListRestaurantResponse {

    private String name;
    private String urlLogo;
}

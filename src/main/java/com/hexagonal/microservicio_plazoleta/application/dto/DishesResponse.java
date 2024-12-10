package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.*;

@Getter
@Setter
public class DishesResponse {

    private String name;

    private Double price;

    private String description;

    private String imageUrl;

    private String category;

   private boolean active = true;
}

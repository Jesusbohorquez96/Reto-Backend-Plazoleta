package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponse {

    private Long id;

    private String name;

    private Integer nit;

    private String address;

    private String phone;

    private String urlLogo;

    private Long ownerId;

}

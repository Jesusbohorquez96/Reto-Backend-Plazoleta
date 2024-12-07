package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class RestaurantRequest {

    @NotBlank(message = NAME_REQUIRED)
    @Pattern(regexp = NUMBERS_NAME, message = NAME_NUMBERS)
    private String name;

    @NotNull(message = NIT_REQUIRED)
    @Digits(integer = MAX_NIT, fraction = ZERO, message = NIT_NUMERIC)
    private Integer nit;

    @NotBlank(message = ADDRESS_REQUIRED)
    private String address;

    @NotBlank(message = PHONE_REQUIRED)
    @Pattern(regexp = PHONE_NUMBER, message = PHONE_INVALID)
    private String phone;

    @NotBlank(message = URL_REQUIRED)
    @URL(message = URL_PAST)
    private String urlLogo;

    @NotNull(message = OWNER_REQUIRED)
    private Long ownerId;

}

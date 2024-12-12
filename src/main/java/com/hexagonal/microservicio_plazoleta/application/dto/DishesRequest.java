package com.hexagonal.microservicio_plazoleta.application.dto;

import lombok.*;
import javax.validation.constraints.*;
import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class DishesRequest {

    @NotBlank(message = NAME_REQUIRED)
    private String name;

    @Positive(message = PRICE_INVALID)
    @NotNull(message = PRICE_MANDATORY)
    private Double price;

    @NotBlank(message = DESCRIPTION_REQUIRED)
    @Size(max = MAX_LENGTH, message = DESCRIPTION_MAX_LENGTH_EXCEEDED)
    private String description;

    @NotBlank(message = URL_REQUIRED)
    @Pattern(regexp = HTTPS, message = URL_IMAGE)
    private String imageUrl;

    @NotBlank(message = CATEGORY_REQUIRED)
    private String category;

    @NotNull(message = RESTAURANT_REQUIRED)
    private Long restaurantId;

    public DishesRequest(String s, double v, String description, String url, String category, long l) {
    }
}

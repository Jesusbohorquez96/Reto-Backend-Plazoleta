package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Entity
@Table(name = "dishes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "restaurant_id"})
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DishesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = NAME_REQUIRED)
    private String name;

    @Column(nullable = false)
    @Positive(message = PRICE_INVALID)
    @NotNull(message = PRICE_MANDATORY)
    private Double price;

    @Column(nullable = false, length = MAX_LENGTH)
    @NotBlank(message = DESCRIPTION_REQUIRED)
    @Size(max = MAX_LENGTH, message = DESCRIPTION_MAX_LENGTH_EXCEEDED)
    private String description;

    @Column(nullable = false)
    @NotBlank(message = URL_REQUIRED)
    @Pattern(
            regexp = HTTPS,
            message = URL_IMAGE
    )
    private String imageUrl;

    @Column(nullable = false)
    @NotBlank(message = CATEGORY_REQUIRED)
    private String category;

    @ManyToOne
    @JoinColumn(name = RESTAURANT_ID, nullable = false)
    private RestaurantEntity restaurant;

    @Column(nullable = false)
    private boolean active = true;
}

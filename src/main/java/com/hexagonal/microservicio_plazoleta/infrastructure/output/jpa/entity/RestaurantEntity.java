package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity;

import lombok.*;
import javax.persistence.*;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Entity
@Table(name = ID_RESTAURANT)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer nit;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String urlLogo;

    @Column(nullable = false)
    private Long ownerId;

}

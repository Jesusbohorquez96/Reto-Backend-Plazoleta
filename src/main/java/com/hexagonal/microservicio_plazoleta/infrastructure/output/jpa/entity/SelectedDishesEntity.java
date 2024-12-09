package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "selected_dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SelectedDishesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long dishId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;
}
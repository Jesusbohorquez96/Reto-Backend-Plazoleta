package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity;

import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Entity
@Table(name = ORDERS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = ORDER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectedDishesEntity> selectedDishes;
}

package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository;

import com.hexagonal.microservicio_plazoleta.domain.Utils.OrderStatus;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    boolean existsByUserIdAndStatusIn(Long userId, List<OrderStatus> statuses);
}

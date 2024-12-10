package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.domain.Utils.OrderStatus;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import com.hexagonal.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public Long saveAndReturnId(Order order) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        OrderEntity savedEntity = orderRepository.save(entity);
        return savedEntity.getId();
    }

    @Override
    public boolean existsByUserIdAndStatuses(Long userId, List<OrderStatus> statuses) {
        return orderRepository.existsByUserIdAndStatusIn(userId, statuses);
    }
}

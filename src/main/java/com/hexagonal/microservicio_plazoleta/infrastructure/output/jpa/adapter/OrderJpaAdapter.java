package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import com.hexagonal.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

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

    @Override
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException(ORDER_NOT_FOUND + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public Page<Order> findOrdersByStatus(OrderStatus status, int page, int size, Long restaurantId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderEntity> orderEntities = orderRepository.findByStatusAndRestaurantId(status, restaurantId, pageRequest);
        return orderEntities.map(orderEntityMapper::toDomainWithBasicFields);
    }

    @Override
    public Order findById(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUND + orderId));
        return orderEntityMapper.toDomainWithBasicFields(orderEntity);
    }

    @Override
    public void save(Order order) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        orderRepository.save(entity);
    }
}

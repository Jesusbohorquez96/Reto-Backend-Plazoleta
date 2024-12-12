package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.OrderResponse;
import com.hexagonal.microservicio_plazoleta.application.mapper.IOrderMapper;
import com.hexagonal.microservicio_plazoleta.domain.api.IOrderServicePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderMapper orderMapper;

    @Override
    public void createOrder(Long userId, OrderRequest orderRequest) {
        orderServicePort.createOrder(userId, orderRequest);
    }

    @Override
    public Long createOrderForUser(Long userId, OrderRequest orderRequest) {
        return orderServicePort.createOrderAndGetId(userId, orderRequest);
    }

    @Override
    public Page<OrderResponse> getOrdersByStatus (OrderStatus  status, int page, int size, Long restaurantId) {
        return orderServicePort.getOrdersByStatus(status, page, size, restaurantId)
                .map(orderMapper::toResponseDto);
    }

    @Override
    public void assignOrder(Long orderId, Long restaurantId, Long employeeId) {
        orderServicePort.assignOrder(orderId, restaurantId, employeeId);
    }
}

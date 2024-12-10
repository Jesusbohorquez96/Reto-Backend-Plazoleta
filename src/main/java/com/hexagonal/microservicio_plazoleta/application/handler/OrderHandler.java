package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;

    @Override
    public void createOrder(Long userId, OrderRequest orderRequest) {
        orderServicePort.createOrder(userId, orderRequest);
    }

    @Override
    public Long createOrderForUser(Long userId, OrderRequest orderRequest) {
        return orderServicePort.createOrderAndGetId(userId, orderRequest);
    }
}

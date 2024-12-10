package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;

public interface IOrderHandler {

    void createOrder(Long userId, OrderRequest orderRequest);

    Long createOrderForUser(Long userId, OrderRequest orderRequest);
}

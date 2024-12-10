package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;

public interface IOrderServicePort {

    void createOrder(Long userId, OrderRequest orderRequest);

    Long createOrderAndGetId(Long userId, OrderRequest orderRequest);
}

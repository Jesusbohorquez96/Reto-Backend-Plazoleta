package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import org.springframework.data.domain.*;

public interface IOrderServicePort {

    void createOrder(Long userId, OrderRequest orderRequest);

    Long createOrderAndGetId(Long userId, OrderRequest orderRequest);

    Page<Order> getOrdersByStatus(OrderStatus status, int page, int size, Long restaurantId);
}

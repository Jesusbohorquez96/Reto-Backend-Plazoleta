package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.OrderResponse;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import org.springframework.data.domain.Page;

public interface IOrderHandler {

    void createOrder(Long userId, OrderRequest orderRequest);

    Long createOrderForUser(Long userId, OrderRequest orderRequest);

    Page<OrderResponse> getOrdersByStatus (OrderStatus status, int page, int size, Long restaurantId);

    void assignOrder(Long orderId, Long restaurantId, Long employeeId);
}

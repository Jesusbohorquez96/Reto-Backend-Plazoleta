package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.OrderResponse;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import org.springframework.data.domain.*;

public interface IOrderServicePort {

    void createOrder(Long userId, OrderRequest orderRequest);

    Long createOrderAndGetId(Long userId, OrderRequest orderRequest);

    Page<OrderResponse> getOrdersByStatus(OrderStatus status, int page, int size, Long restaurantId);

    void assignOrder(Long orderId, Long restaurantId, Long employeeId);

    void changeOrderStatus(Long orderId, Long userId, OrderStatus status);
}

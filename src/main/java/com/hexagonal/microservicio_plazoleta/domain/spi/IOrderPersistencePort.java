package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderResponse;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import org.springframework.data.domain.*;

import java.util.List;

public interface IOrderPersistencePort {

    Long saveAndReturnId(Order order);

    boolean existsByUserIdAndStatuses(Long userId, List<OrderStatus> statuses);

    void deleteOrder(Long orderId);

    Page<OrderResponse> findOrdersByStatus(OrderStatus status, int page, int size, Long restaurantId);

    Order findById(Long orderId);

    void save(Order order);

    void updateOrderStatus(Long orderId, OrderStatus status);
}

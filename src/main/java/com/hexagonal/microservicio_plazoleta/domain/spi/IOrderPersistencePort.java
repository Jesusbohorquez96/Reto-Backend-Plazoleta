package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.domain.Utils.OrderStatus;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {

    Long saveAndReturnId(Order order);

    boolean existsByUserIdAndStatuses(Long userId, List<OrderStatus> statuses);
}

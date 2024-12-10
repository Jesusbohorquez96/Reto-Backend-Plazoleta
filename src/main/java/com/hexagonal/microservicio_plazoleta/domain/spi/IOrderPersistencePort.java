package com.hexagonal.microservicio_plazoleta.domain.spi;

import com.hexagonal.microservicio_plazoleta.domain.model.Order;

public interface IOrderPersistencePort {

    Long saveAndReturnId(Order order);
}

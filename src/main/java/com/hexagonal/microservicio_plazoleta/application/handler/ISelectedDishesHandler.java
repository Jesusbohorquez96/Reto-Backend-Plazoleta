package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;

public interface ISelectedDishesHandler {

    void addDishesToOrder(Long userId, OrderRequest  orderRequest);

}

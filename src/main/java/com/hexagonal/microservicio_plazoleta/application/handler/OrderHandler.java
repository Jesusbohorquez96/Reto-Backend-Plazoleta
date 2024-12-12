package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.EmployeeRestaurantIdResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.OrderResponse;
import com.hexagonal.microservicio_plazoleta.domain.api.IOrderServicePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.adapters.services.UsersClientService;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final UsersClientService usersClientService;

    @Override
    public void createOrder(Long userId, OrderRequest orderRequest) {
        if (orderRequest == null || orderRequest.getSelectedDishes() == null || orderRequest.getSelectedDishes().isEmpty()) {
            throw new IllegalArgumentException(ORDER_MUST_DISH_SELECTED);
        }
        if (orderRequest.getRestaurantId() == null) {
            throw new IllegalArgumentException(RESTAURANT_ID_REQUIRED);
        }
        orderServicePort.createOrder(userId, orderRequest);
    }

    @Override
    public Long createOrderForUser(Long userId, OrderRequest orderRequest) {
        return orderServicePort.createOrderAndGetId(userId, orderRequest);
    }

    @Override
    public Page<OrderResponse> getOrdersByStatus (Long userId, OrderStatus status, int page, int size) {
        EmployeeRestaurantIdResponse employee = usersClientService.validateEmployee(userId);
        if (employee == null) {
            throw new IllegalArgumentException(EMPLOYEE_NOT_VALID);
        }

        return orderServicePort.getOrdersByStatus(status, page, size, employee.getRestaurantId());
    }

    @Override
    public void assignOrder(Long orderId, Long userId) {
        EmployeeRestaurantIdResponse employee = usersClientService.validateEmployee(userId);
        if (employee == null) {
            throw new IllegalArgumentException(EMPLOYEE_NOT_VALID_PERMISSIONS);
        }
        orderServicePort.assignOrder(orderId, employee.getRestaurantId(), userId);
    }

    @Override
    public void changeOrderStatus(Long orderId, Long userId, OrderStatus status) {
        orderServicePort.changeOrderStatus(orderId, userId, status);
    }
}

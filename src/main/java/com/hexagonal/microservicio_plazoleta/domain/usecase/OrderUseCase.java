package com.hexagonal.microservicio_plazoleta.domain.usecase;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import com.hexagonal.microservicio_plazoleta.domain.api.IOrderServicePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.adapters.services.SelectedDishService;
import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.SelectedValidator;
import com.hexagonal.microservicio_plazoleta.infrastructure.exception.NotFoundException;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.stream.Collectors;
import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IDishesPersistencePort dishesPersistencePort;
    private final SelectedDishService selectedDishService;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IDishesPersistencePort dishesPersistencePort, SelectedDishService selectedDishService) {
        this.orderPersistencePort = orderPersistencePort;
        this.dishesPersistencePort = dishesPersistencePort;
        this.selectedDishService = selectedDishService;
    }

    @Override
    public void createOrder(Long userId, OrderRequest orderRequest) {

        validateOrder(userId, orderRequest);

        List<Dishes> dishes = orderRequest.getSelectedDishes().stream()
                .map(request -> dishesPersistencePort.getDishById(request.getDishId()))
                .collect(Collectors.toList());

        SelectedValidator.validateSameRestaurant(dishes);

        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantId(orderRequest.getRestaurantId());
        order.setStatus(OrderStatus.PENDING);

        List<SelectedDish> selectedDishes = selectedDishService.mapAndValidateDishes(dishes, orderRequest, order);

        try {
            Long orderId = orderPersistencePort.saveAndReturnId(order);
            order.setId(orderId);
            selectedDishes.forEach(dish -> dish.setOrder(order));
            selectedDishService.saveSelectedDishes(selectedDishes);
        } catch (Exception e) {
            if (order.getId() != null) {
                orderPersistencePort.deleteOrder(order.getId());
            }
            throw e;
        }
    }

    @Override
    public Long createOrderAndGetId(Long userId, OrderRequest orderRequest) {
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.PENDING);
        order.setRestaurantId(orderRequest.getRestaurantId());
        return orderPersistencePort.saveAndReturnId(order);
    }

    private void validateOrder(Long userId, OrderRequest orderRequest) {
        List<OrderStatus> statusesInProcess = List.of(OrderStatus.PENDING, OrderStatus.IN_PROGRESS, OrderStatus.DELIVERED);
        if (orderPersistencePort.existsByUserIdAndStatuses(userId, statusesInProcess)) {
            throw new IllegalArgumentException(ORDER_PROCESS);
        }

        if (orderRequest.getSelectedDishes() == null || orderRequest.getSelectedDishes().isEmpty()) {
            throw new IllegalArgumentException(ORDER_MUST_DISH);
        }

        if (!dishesPersistencePort.existsById(orderRequest.getRestaurantId())) {
            throw new NotFoundException(RESTAURANT_NOT_EXIST);
        }
    }

    @Override
    public Page<Order> getOrdersByStatus(OrderStatus status, int page, int size, Long restaurantId) {
        return orderPersistencePort.findOrdersByStatus(status, page, size, restaurantId);
    }

    @Override
    public void assignOrder(Long orderId, Long restaurantId, Long employeeId) {
        Order order = orderPersistencePort.findById(orderId);
        if (order == null) {
            throw new NotFoundException(ORDER_NOT_FOUND);
        }

        if (!order.getRestaurantId().equals(restaurantId)) {
            throw new IllegalArgumentException(ORDER_NOT_FOUND);
        }

        order.setSelectedDishes(null);
        order.setEmployeeIdAssigned(employeeId);
        orderPersistencePort.save(order);
    }
}

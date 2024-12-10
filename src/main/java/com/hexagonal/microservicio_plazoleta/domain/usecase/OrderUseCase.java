package com.hexagonal.microservicio_plazoleta.domain.usecase;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.domain.Utils.OrderStatus;
import com.hexagonal.microservicio_plazoleta.domain.api.IOrderServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.Utils.SelectedValidator;
import com.hexagonal.microservicio_plazoleta.domain.spi.SelectedDishPersistencePort;
import com.hexagonal.microservicio_plazoleta.infrastructure.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IDishesPersistencePort dishesPersistencePort;
    private final SelectedDishPersistencePort selectedDishPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IDishesPersistencePort dishesPersistencePort, SelectedDishPersistencePort selectedDishPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.dishesPersistencePort = dishesPersistencePort;
        this.selectedDishPersistencePort = selectedDishPersistencePort;
    }

    @Override
    public void createOrder(Long userId, OrderRequest orderRequest) {

        if (orderRequest.getSelectedDishes() == null || orderRequest.getSelectedDishes().isEmpty()) {
            throw new IllegalArgumentException(ORDER_MUST_DISH);
        }

        if (!dishesPersistencePort.existsById(orderRequest.getRestaurantId())) {
            throw new NotFoundException(RESTAURANT_NOT_EXIST);
        }

        List<Dishes> dishes = orderRequest.getSelectedDishes().stream()
                .map(request -> dishesPersistencePort.getDishById(request.getDishId()))
                .collect(Collectors.toList());

        SelectedValidator.validateSameRestaurant(dishes);

        Order order = new Order();
        order.setRestaurantId(orderRequest.getRestaurantId());
        order.setUserId(userId);
        order.setStatus(OrderStatus.PENDING);

        Long saveOrden = orderPersistencePort.saveAndReturnId(order);

        order.setId(saveOrden);
        List<SelectedDish> selectedDishes = orderRequest.getSelectedDishes().stream()
                .map(request -> {
                    Dishes dish = dishes.stream()
                            .filter(d -> d.getId().equals(request.getDishId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException(DISH_NOT_FOUNT));

                    SelectedValidator.validateDishIsActive(dish);
                    return new SelectedDish(
                            null,
                            dish.getId(),
                            userId,
                            request.getQuantity(),
                            SelectedValidator.calculateTotalPrice(dish.getPrice(), request.getQuantity()),
                            order
                    );

                }).collect(Collectors.toList());
        selectedDishes.forEach(request -> {
            selectedDishPersistencePort.saveSelectedDish(request);
        });
    }

    @Override
    public Long createOrderAndGetId(Long userId, OrderRequest orderRequest) {
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.PENDING);
        order.setRestaurantId(orderRequest.getRestaurantId());
        return orderPersistencePort.saveAndReturnId(order);
    }
}
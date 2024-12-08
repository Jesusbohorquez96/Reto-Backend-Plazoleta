package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.DishStatusUpdateRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.DishesRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.DishesUpdateRequest;
import com.hexagonal.microservicio_plazoleta.application.mapper.DishesRequestMapper;
import com.hexagonal.microservicio_plazoleta.domain.api.IDishesServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishesHandler implements IDishesHandler{

    private final DishesRequestMapper dishesRequestMapper;
    private final IDishesServicePort dishesServicePort;

    @Override
    public void saveDishes(DishesRequest dishesRequest) {
        String ownerId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Dishes dishes = dishesRequestMapper.toDishes(dishesRequest, Long.parseLong(ownerId));
        dishesServicePort.saveDishes(dishes);
    }

    @Override
    public void updateDish(Long dishId, DishesUpdateRequest request) {
        String ownerId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        dishesServicePort.updateDish(dishId, request.getPrice(), request.getDescription(), Long.parseLong(ownerId));
    }

    @Override
    public void updateDishStatus(Long dishId, DishStatusUpdateRequest request) {
        String ownerId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        dishesServicePort.updateDishStatus(dishId, request.getActive(), Long.parseLong(ownerId));
    }
}

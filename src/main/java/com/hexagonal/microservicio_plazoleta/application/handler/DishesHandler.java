package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.DishesRequest;
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
}

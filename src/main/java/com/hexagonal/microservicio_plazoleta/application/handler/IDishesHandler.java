package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.DishStatusUpdateRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.DishesRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.DishesUpdateRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.ListDishResponse;
import org.springframework.data.domain.*;

public interface IDishesHandler {

    void saveDishes (DishesRequest dishesRequest);

    void updateDish(Long dishId, DishesUpdateRequest request);

    void updateDishStatus(Long dishId, DishStatusUpdateRequest request);

    Page<ListDishResponse> getDishesByRestaurantId(Long restaurantId, String category, Pageable pageable);
}

package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.DishesEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.*;


@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishesEntityMapper {

    @Mapping(source =  "restaurantId",target = "restaurant")
    DishesEntity toEntity(Dishes dishesModel);

    @Mapping(source = "restaurant.id", target = "restaurantId")
    Dishes toDishes(DishesEntity dishesEntity);

    default RestaurantEntity mapRestaurant(Long restaurantId) {
        if (restaurantId == null) {
            return null;
        }
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setId(restaurantId);
        return restaurant;
    }

}

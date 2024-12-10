package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper;

import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.DishesEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;
import static org.mapstruct.MappingConstants.ComponentModel.*;


@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishesEntityMapper {

    @Mapping(source = RESTAURANT_ID_MAPPER, target = ID_RESTAURANT)
    DishesEntity toEntity(Dishes dishesModel);

    @Mapping(source = RESTAURANT_ID_SOURCE, target = RESTAURANT_ID_MAPPER)
    @Mapping(source = ID_RESTAURANT_OWNER, target = ID_OWNER)
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

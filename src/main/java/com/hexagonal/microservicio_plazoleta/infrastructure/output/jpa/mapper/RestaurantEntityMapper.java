package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper;

import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {

    RestaurantEntity toEntity(Restaurant restaurantModel);

    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

}

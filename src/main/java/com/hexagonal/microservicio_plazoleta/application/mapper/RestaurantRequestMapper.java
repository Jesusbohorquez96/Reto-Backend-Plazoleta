package com.hexagonal.microservicio_plazoleta.application.mapper;

import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestMapper {

    @Mapping(target = "ownerId", source = "ownerId")
    Restaurant toRestaurant(RestaurantRequest restaurantRequest);
}

package com.hexagonal.microservicio_plazoleta.application.mapper;

import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestMapper {

    @Mapping(target = ID_OWNER, source = ID_OWNER)
    Restaurant toRestaurant(RestaurantRequest restaurantRequest);
}

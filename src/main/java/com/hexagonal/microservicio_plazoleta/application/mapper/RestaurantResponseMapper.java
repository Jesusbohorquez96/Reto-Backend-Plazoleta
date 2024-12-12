package com.hexagonal.microservicio_plazoleta.application.mapper;

import com.hexagonal.microservicio_plazoleta.application.dto.IdRestaurantResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantResponse;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantResponseMapper {

    RestaurantResponse toRestaurantResponse(Restaurant entity);

    @Mapping(source = ID, target = RESTAURANT_ID_MAPPER)
    @Mapping(source = ID_OWNER, target = ID_OWNER)
    IdRestaurantResponse toIdResponse(Restaurant restaurantId);
}

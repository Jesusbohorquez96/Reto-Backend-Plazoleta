package com.hexagonal.microservicio_plazoleta.application.mapper;

import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantResponse;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantResponseMapper {

    RestaurantResponse toRestaurantResponse(Restaurant entity);
}

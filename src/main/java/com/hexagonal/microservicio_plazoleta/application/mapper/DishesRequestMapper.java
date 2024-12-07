package com.hexagonal.microservicio_plazoleta.application.mapper;

import com.hexagonal.microservicio_plazoleta.application.dto.DishesRequest;
import com.hexagonal.microservicio_plazoleta.domain.model.Dishes;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishesRequestMapper {

    Dishes toDishes(DishesRequest dishesRequest, Long ownerId);

}

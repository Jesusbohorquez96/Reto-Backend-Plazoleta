package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper;

import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

    @Mapping(target = SELECTED_DISHES, source = SELECTED_DISHES)
    OrderEntity toEntity(Order order);

    @Mapping(target = SELECTED_DISHES, source = SELECTED_DISHES)
    Order toDomain(OrderEntity orderEntity);
}

package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper;

import com.hexagonal.microservicio_plazoleta.domain.model.SelectedDish;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.SelectedDishesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.*;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SelectedDishEntityMapper {

    SelectedDishesEntity toEntity(SelectedDish selectedDish);

    SelectedDish toDomain(SelectedDishesEntity selectedDishEntity);
}


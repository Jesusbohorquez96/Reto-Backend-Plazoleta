package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository;

import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.SelectedDishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectedDishesRepository extends JpaRepository<SelectedDishesEntity, Long> {

}

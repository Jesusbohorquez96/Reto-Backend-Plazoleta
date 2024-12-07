package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository;

import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishesRepository extends JpaRepository<DishesEntity, Long> {

}

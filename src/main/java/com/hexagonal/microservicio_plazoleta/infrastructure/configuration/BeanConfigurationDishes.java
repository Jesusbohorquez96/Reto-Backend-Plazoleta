package com.hexagonal.microservicio_plazoleta.infrastructure.configuration;

import com.hexagonal.microservicio_plazoleta.domain.api.IDishesServicePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.usecase.DishesUseCase;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter.DishesJpaAdapter;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.DishesEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IDishesRepository;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationDishes {

    private final IDishesRepository dishesRepository;
    private final DishesEntityMapper dishesEntityMapper;
    private final IRestaurantRepository restaurantRepository;

    @Bean
    public IDishesPersistencePort dishesPersistencePort() {
        return new DishesJpaAdapter(restaurantRepository, dishesRepository, dishesEntityMapper);
    }

    @Bean
    public IDishesServicePort dishesServicePort() {
        return new DishesUseCase(dishesPersistencePort()) {
        };
    }
}

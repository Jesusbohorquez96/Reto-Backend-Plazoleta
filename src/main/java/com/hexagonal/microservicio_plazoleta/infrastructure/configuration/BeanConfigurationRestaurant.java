package com.hexagonal.microservicio_plazoleta.infrastructure.configuration;

import com.hexagonal.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Restaurant;
import com.hexagonal.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.usecase.RestaurantUseCase;
import com.hexagonal.microservicio_plazoleta.infrastructure.adapters.feign.UsersClient;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationRestaurant {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort()) {
        };
    }
}

package com.hexagonal.microservicio_plazoleta.infrastructure.configuration;

import com.hexagonal.microservicio_plazoleta.domain.api.IOrderServicePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.SelectedDishPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.usecase.OrderUseCase;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter.OrderJpaAdapter;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationOrder {

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final IDishesPersistencePort dishesPersistencePort;
    private final SelectedDishPersistencePort selectedDishPersistencePort;

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(orderPersistencePort(), dishesPersistencePort,selectedDishPersistencePort);
    }
}

package com.hexagonal.microservicio_plazoleta.infrastructure.configuration;

import com.hexagonal.microservicio_plazoleta.domain.api.ISelectedDishServicePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.IDishesPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.spi.SelectedDishPersistencePort;
import com.hexagonal.microservicio_plazoleta.domain.usecase.SelectedDishUseCase;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter.SelectedDishJpaAdapter;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.SelectedDishesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationSelected {

    private final SelectedDishesRepository selectedDishesRepository;
    private final IDishesPersistencePort dishesPersistencePort;

    @Bean
    public SelectedDishPersistencePort selectedDishPersistencePort() {
        return new SelectedDishJpaAdapter(selectedDishesRepository);
    }

    @Bean
    public ISelectedDishServicePort selectedDishServicePort() {
        return new SelectedDishUseCase(selectedDishPersistencePort(), dishesPersistencePort);
    }
}
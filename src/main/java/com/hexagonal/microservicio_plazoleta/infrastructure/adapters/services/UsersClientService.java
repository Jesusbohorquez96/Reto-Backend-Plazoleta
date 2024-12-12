package com.hexagonal.microservicio_plazoleta.infrastructure.adapters.services;

import com.hexagonal.microservicio_plazoleta.application.dto.EmployeeRestaurantIdResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.OwnerResponse;
import com.hexagonal.microservicio_plazoleta.infrastructure.adapters.feign.UsersClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersClientService {

    private final UsersClient usersClient;

    public OwnerResponse validateOwner(Long ownerId) {
        return usersClient.validateOwner(ownerId);
    }

    public EmployeeRestaurantIdResponse validateEmployee(Long employeeId) {
        return usersClient.validateEmployee(employeeId);
    }
}

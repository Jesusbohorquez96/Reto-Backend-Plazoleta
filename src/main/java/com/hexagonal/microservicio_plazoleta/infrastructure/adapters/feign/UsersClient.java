package com.hexagonal.microservicio_plazoleta.infrastructure.adapters.feign;

import com.hexagonal.microservicio_plazoleta.application.dto.EmployeeRestaurantIdResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.OwnerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@FeignClient(name = MICROSERVICES_USERS, url = HTTP_USERS)
public interface UsersClient {

    @GetMapping(VALIDATE_OWNER)
    OwnerResponse validateOwner(@PathVariable(ID_OWNER) Long ownerId);

    @GetMapping(VALIDATE_EMPLOYEE)
    EmployeeRestaurantIdResponse validateEmployee(@PathVariable(EMPLOYEE_ID) Long employeeId);
}

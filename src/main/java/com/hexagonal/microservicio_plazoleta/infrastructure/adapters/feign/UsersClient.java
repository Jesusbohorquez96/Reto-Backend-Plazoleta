package com.hexagonal.microservicio_plazoleta.infrastructure.adapters.feign;

import com.hexagonal.microservicio_plazoleta.application.dto.OwnerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservices-users", url = "http://localhost:8081/users")
public interface UsersClient {

    @GetMapping("/validate-owner/{ownerId}")
    OwnerResponse validateOwner(@PathVariable("ownerId") Long ownerId);
}

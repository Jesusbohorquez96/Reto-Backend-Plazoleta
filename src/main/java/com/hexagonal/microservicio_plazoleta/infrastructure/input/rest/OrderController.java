package com.hexagonal.microservicio_plazoleta.infrastructure.input.rest;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.application.handler.IOrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Tag(name = "Orders", description = "Operations related to managing orders")
@RestController
@RequestMapping(API_ORDERS)
@RequiredArgsConstructor
public class OrderController {

    private final IOrderHandler orderHandler;

    @Operation(
            summary = "Create a new order",
            description = "Allows customers to create a new order specifying the restaurant and selected dishes"
    )
    @PostMapping
    @PreAuthorize(ROL_CUSTOMER)
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderHandler.createOrder(Long.parseLong(userId), orderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
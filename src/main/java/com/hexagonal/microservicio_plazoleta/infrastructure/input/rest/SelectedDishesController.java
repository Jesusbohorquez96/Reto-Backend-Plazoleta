package com.hexagonal.microservicio_plazoleta.infrastructure.input.rest;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.application.handler.IOrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Tag(name = "Selected Dishes", description = "Operations related to selecting dishes for a user")
@RestController
@RequestMapping(ALL_API_DISHES)
@RequiredArgsConstructor
public class SelectedDishesController {

    private final IOrderHandler orderHandler;

    @Operation(summary = "Select a dish", description = "Allows clients to select a dish and specify its quantity")
    @PreAuthorize(ROL_CUSTOMER)
    @PostMapping
    public ResponseEntity<Void> addDishToSelection(
            @Valid @RequestBody OrderRequest  orderRequest
    ) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userId == null) {
            throw new IllegalArgumentException(USER_SECURITY);
        }
        try {
            Long clientId = Long.parseLong(userId);
            orderHandler.createOrder(clientId, orderRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(ERROR_HANDLER, e);
        }
    }
}

package com.hexagonal.microservicio_plazoleta.infrastructure.input.rest;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.application.handler.IOrderHandler;
import com.hexagonal.microservicio_plazoleta.infrastructure.exception.DishValidationException;
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
    public ResponseEntity<String> addDishToSelection(
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
        } catch (DishValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Validation failed for Dish ID: " + e.getDishId() + ". Reason: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ERROR_HANDLER + ": " + e.getMessage());
        }
    }
}

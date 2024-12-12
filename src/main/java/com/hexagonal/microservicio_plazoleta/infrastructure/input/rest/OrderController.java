package com.hexagonal.microservicio_plazoleta.infrastructure.input.rest;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.OrderResponse;
import com.hexagonal.microservicio_plazoleta.application.handler.IOrderHandler;
import com.hexagonal.microservicio_plazoleta.infrastructure.adapters.services.UsersClientService;
import com.hexagonal.microservicio_plazoleta.infrastructure.exception.DishValidationException;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.AuthenticationService;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;
@Slf4j
@Tag(name = "Orders", description = "Operations related to managing orders")
@RestController
@RequestMapping(API_ORDERS)
@RequiredArgsConstructor
public class OrderController {

    private final IOrderHandler orderHandler;
    private final UsersClientService usersClientService;
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Create a new order",
            description = "Allows customers to create a new order specifying the restaurant and selected dishes"
    )
    @PreAuthorize(ROL_CUSTOMER)
    @PostMapping
    public ResponseEntity<String> createOrder(
            @Valid @RequestBody OrderRequest  orderRequest
    ) {
        Long userId = authenticationService.getAuthenticatedUserId();
        try {
            orderHandler.createOrder(userId, orderRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DishValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(VALIDATION_DISH_ID + e.getDishId() + REASON + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ERROR_HANDLER + ": " + e.getMessage());
        }
    }
    
    @Operation(
            summary = "Get orders by restaurant and status",
            description = "Retrieve a paginated list of orders for a specific restaurant, filtered by status if provided"
    )
    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getOrdersByStatus(
            @RequestParam OrderStatus status,
            @RequestParam int page,
            @RequestParam int size) {
        Long userId = authenticationService.getAuthenticatedUserId();
        Page<OrderResponse> orders = orderHandler.getOrdersByStatus(userId, status, page, size);
        return ResponseEntity.ok(orders);
    }

    @Operation(
            summary = "Assign order to restaurant employee",
            description = "Assign an order to a restaurant employee for preparation"
    )
    @PreAuthorize(ROL_EMPLOYEE)
    @PutMapping(ASSIGN_EMPLOYEE)
    public ResponseEntity<String> assignOrder(
            @PathVariable Long orderId
    ) {
        Long userId = authenticationService.getAuthenticatedUserId();
        orderHandler.assignOrder(orderId, userId);
        return ResponseEntity.ok("Order assigned successfully.");
    }

    @Operation(
            summary = "Change order status",
            description = "Change the status of an order"
    )
    @PutMapping(CHANGE_STATUS)
    public ResponseEntity<String> changeOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status
    ) {
        orderHandler.changeOrderStatus(orderId, Long.valueOf((String)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()), status);
                return new ResponseEntity<>(HttpStatus.OK);
    }
}
package com.hexagonal.microservicio_plazoleta.infrastructure.input.rest;

import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;
import com.hexagonal.microservicio_plazoleta.application.handler.IRestaurantHandler;
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

@Tag(name = RESTAURANTS_API)
@RestController
@RequestMapping(RESTAURANTS)
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Create a new restaurant", description = "Add a new restaurant to the system")
    @PreAuthorize(ROL_OWNER)
    @PostMapping(CREATE)
    public ResponseEntity<Void> saveRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest) {
        String ownerId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        restaurantRequest.setOwnerId(Long.parseLong(ownerId));
        restaurantHandler.saveRestaurant(restaurantRequest);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
}

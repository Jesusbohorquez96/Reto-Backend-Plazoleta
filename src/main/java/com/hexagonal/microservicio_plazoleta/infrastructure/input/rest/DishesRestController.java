package com.hexagonal.microservicio_plazoleta.infrastructure.input.rest;

import com.hexagonal.microservicio_plazoleta.application.dto.DishesRequest;
import com.hexagonal.microservicio_plazoleta.application.handler.IDishesHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Tag(name = DISHES_API)
@RestController
@RequestMapping(DISHES)
@RequiredArgsConstructor
public class DishesRestController {

    private final IDishesHandler dishesHandler;

    @Operation(summary = "Create a new Dishes", description = "Add a new Dishes to the system")
    @PreAuthorize(ROL_OWNER)
    @PostMapping(CREATE)
    public ResponseEntity<Void> saveDishes(@RequestBody @Valid DishesRequest dishesRequest){
        dishesHandler.saveDishes(dishesRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

package com.hexagonal.microservicio_plazoleta.infrastructure.input.rest;

import com.hexagonal.microservicio_plazoleta.application.dto.ListRestaurantResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.OwnerResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.RestaurantRequest;
import com.hexagonal.microservicio_plazoleta.application.handler.IRestaurantHandler;
import com.hexagonal.microservicio_plazoleta.domain.api.UsersClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;

import java.util.Objects;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Tag(name = RESTAURANTS_API)
@RestController
@RequestMapping(RESTAURANTS)
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;
    private final UsersClientService usersClientService;

    @Operation(summary = "Create a new restaurant", description = "Add a new restaurant to the system")
    @PreAuthorize(ROL_ADMIN)
    @PostMapping(CREATE)
    public ResponseEntity<Void> saveRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest) {
        OwnerResponse owner = usersClientService.validateOwner(restaurantRequest.getOwnerId());
        if (!Objects.equals(owner.getId(), restaurantRequest.getOwnerId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        restaurantHandler.saveRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "List restaurants paginated and sorted by name")
    @GetMapping
    public Page<ListRestaurantResponse> listRestaurants(Pageable pageable) {
        return restaurantHandler.listRestaurants(pageable);
    }
}

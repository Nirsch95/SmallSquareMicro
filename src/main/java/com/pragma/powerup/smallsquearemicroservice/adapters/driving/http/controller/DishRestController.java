package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IDishHandler;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class DishRestController {
    private final IDishHandler dishHandler;

    @Operation(summary = "Add a new dish",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Dish created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Dish already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
    @PostMapping("")
    public ResponseEntity<Map<String, String>> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto){
        dishHandler.saveDish(dishRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.DISH_CREATED_MESSAGE));
    }


    @Operation(summary = "Update a dish",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dish Updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DishUpdateRequestDto.class))),
                    @ApiResponse(responseCode = "404", description = "Dish not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
    @PatchMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateDish(@PathVariable Long id, @Schema(implementation = DishUpdateRequestDto.class) @RequestBody DishRequestDto dishRequestDto) {
        dishHandler.updateDish(id, dishRequestDto);
        return ResponseEntity.ok()
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.DISH_UPDATED_MESSAGE));
    }

    @Operation(summary = "Update a dish",
              responses = {
        @ApiResponse(responseCode = "200", description = "Dish Updated",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = DishUpdateRequestDto.class))),
        @ApiResponse(responseCode = "404", description = "Dish not found",
                content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @PatchMapping("/state/{id}")
    public ResponseEntity<Map<String, String>> changeStateDish(@PathVariable Long id) {
        dishHandler.changeStateDish(id);
        return ResponseEntity.ok()
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.DISH_UPDATED_MESSAGE));
    }

    @Operation(summary = "Get all dishes of the restaurant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All dishes of the restaurant",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DishUpdateRequestDto.class))),
                    @ApiResponse(responseCode = "404", description = "Dish not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
    @GetMapping("/all/{idRestaurant}")
    public ResponseEntity<List<DishResponseDto>> getDishes(@PathVariable Long idRestaurant, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("category") Long category){
        return ResponseEntity.ok(dishHandler.getDishes(idRestaurant, page, size, category));
    }
}

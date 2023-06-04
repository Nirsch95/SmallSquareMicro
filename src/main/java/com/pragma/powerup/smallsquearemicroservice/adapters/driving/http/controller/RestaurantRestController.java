package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.UserDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class RestaurantRestController {
    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a new restaurant",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Restaurant created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Restaurant already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
    @PostMapping("")
    public ResponseEntity<Map<String, String>> saveRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto){
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.RESTAURANT_CREATED_MESSAGE));
    }

    @Operation(summary = "Get all the restaurants",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All restaurant returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = RestaurantSummaryDto.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/all/{page}")
    public ResponseEntity<List<RestaurantSummaryDto>> getRestaurants(@PathVariable int page, @RequestParam("size") int size){
        return ResponseEntity.ok(restaurantHandler.getRestaurants(page, size));
    }

    @Operation(summary = "Add a employee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Employee is added",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Restaurant already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
    @PostMapping("/add/{restaurantId}")
    public ResponseEntity<Map<String, String>> addEmployee(@PathVariable Long restaurantId, @RequestBody UserDto userDto){
        restaurantHandler.addEmployee(restaurantId, userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.RESTAURANT_CREATED_MESSAGE));
    }
}

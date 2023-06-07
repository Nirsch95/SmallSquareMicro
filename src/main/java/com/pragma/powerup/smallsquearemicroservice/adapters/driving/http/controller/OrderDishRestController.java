package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.OrderDishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IOrderDishHandler;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/orderdish")
@RequiredArgsConstructor
public class OrderDishRestController {
    private final IOrderDishHandler orderDishHandler;
    @Operation(summary = "Add a dish to the order",
            responses = {
                    @ApiResponse(responseCode = "201", description = "OrderDish created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "OrderDish already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
    @PostMapping("orderdish")
    public ResponseEntity<Map<String, String>> saveOrderDish(@RequestBody OrderDishRequestDto orderDishRequestDto){
        orderDishHandler.saveOrderDish(orderDishRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ORDERDISH_CREATED_MESSAGE));
    }
}

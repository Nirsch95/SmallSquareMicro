package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IDishHandler;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DishRestControllerTest {

    private DishRestController dishRestController;

    @Mock
    private IDishHandler dishHandler;

    @BeforeEach
    void setUp() {
        dishHandler = Mockito.mock(IDishHandler.class);
        dishRestController = new DishRestController(dishHandler);
    }

    @Test
    void saveDish_WhenValidDishRequestDto_ReturnsCreatedResponse() {
        // Arrange
        DishRequestDto dto = new DishRequestDto("DogHouse", 1L, "Fast Food", 1000, 1L, "restaurante/image.com");
        Mockito.doNothing().when(dishHandler).saveDish(dto);

        // Act
        ResponseEntity<Map<String, String>> response = dishRestController.saveDish(dto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.DISH_CREATED_MESSAGE), response.getBody());
        verify(dishHandler).saveDish(dto);
    }

    @Test
    void updateDish_WhenValidDishRequestDto_ReturnsOkResponse() {
        // Arrange
        Long dishId = 1L;
        DishRequestDto dishRequestDto = new DishRequestDto("Updated Dish", 1L, "Updated Category", 2000, 1L, "image.com");
        Mockito.doNothing().when(dishHandler).updateDish(dishId, dishRequestDto);

        // Act
        ResponseEntity<Map<String, String>> response = dishRestController.updateDish(dishId, dishRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.DISH_UPDATED_MESSAGE), response.getBody());
        verify(dishHandler).updateDish(dishId, dishRequestDto);
    }

    @Test
    void changeStateDish_WhenValidDishId_ReturnsOkResponse() {
        // Arrange
        Long dishId = 1L;
        Mockito.doNothing().when(dishHandler).changeStateDish(dishId);

        // Act
        ResponseEntity<Map<String, String>> response = dishRestController.changeStateDish(dishId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.DISH_UPDATED_MESSAGE), response.getBody());
        verify(dishHandler).changeStateDish(dishId);
    }
}
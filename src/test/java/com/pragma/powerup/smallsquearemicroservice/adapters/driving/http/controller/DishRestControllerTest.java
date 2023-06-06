package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.DishResponseDto;
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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    void testGetDishes_ValidParameters_DishesReturned() {
        // Arrange
        Long idRestaurant = 1L;
        int page = 1;
        int size = 10;
        Long category = 2L;
        List<DishResponseDto> expectedDishes = Collections.singletonList(new DishResponseDto("Dish", 1L,
                "Dish", 15000, 1L, "dish.jpg", true));
        when(dishHandler.getDishes(idRestaurant, page, size, category)).thenReturn(expectedDishes);

        // Act
        ResponseEntity<List<DishResponseDto>> response = dishRestController.getDishes(idRestaurant, page, size, category);

        // Assert
        verify(dishHandler, times(1)).getDishes(idRestaurant, page, size, category);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDishes, response.getBody());
    }

    @Test
    void testGetDishes_DishNotFound_ReturnError() {
        // Arrange
        Long idRestaurant = 1L;
        int page = 1;
        int size = 10;
        Long category = 2L;

        when(dishHandler.getDishes(idRestaurant, page, size, category)).thenThrow(new NoDataFoundException());

        // Act
        assertThrows(NoDataFoundException.class, () -> dishRestController.getDishes(idRestaurant, page, size, category));

        // Assert
        verify(dishHandler, times(1)).getDishes(idRestaurant, page, size, category);
    }
}
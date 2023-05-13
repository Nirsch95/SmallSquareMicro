package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.CategoryRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IDishHandler;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DishRestControllerTest {

    @Mock
    private IDishHandler dishHandler;

    @InjectMocks
    private DishRestController dishRestController;

    private DishRequestDto dishRequestDto;

    private DishUpdateRequestDto dishUpdateRequestDto;

    @BeforeEach
    void setUp() {
        DishRequestDto dto = new DishRequestDto("DogHouse", 1L, "Fast Food", 1000, 1L, "restaurante/image.com");
    }

    @Test
    @DisplayName("Given a valid dish, when saveDish is called, then a CREATED response is returned")
    void testSaveDish() {
        // Arrange
        Mockito.doNothing().when(dishHandler).saveDish(dishRequestDto);

        // Act
        ResponseEntity<Map<String, String>> responseEntity = dishRestController.saveDish(dishRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(Constants.DISH_CREATED_MESSAGE, responseEntity.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
    }

    @Test
    @DisplayName("Given a valid dish, when saveDish is called, then a CREATED response is returned")
    void testUpdateDish() {
        // Arrange
        Mockito.doNothing().when(dishHandler).updateDish(1L, dishRequestDto);

        // Act
        ResponseEntity<Map<String, String>> responseEntity = dishRestController.updateDish(1L, dishRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Constants.DISH_UPDATED_MESSAGE, responseEntity.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
    }
}
package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.CategoryRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.ICategoryHandler;
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
class CategoryRestControllerTest {
    @Mock
    private ICategoryHandler categoryHandler;

    @InjectMocks
    private CategoryRestController categoryRestController;

    private CategoryRequestDto categoryRequestDto;

    @BeforeEach
    void setUp() {
        CategoryRequestDto dto = new CategoryRequestDto("Fast Food", "Fast Food");
    }

    @Test
    @DisplayName("Given a valid category, when saveCategory is called, then a CREATED response is returned")
    void testSaveCategory() {
        // Arrange
        Mockito.doNothing().when(categoryHandler).saveCategory(categoryRequestDto);

        // Act
        ResponseEntity<Map<String, String>> responseEntity = categoryRestController.saveCategory(categoryRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(Constants.CATEGORY_CREATED_MESSAGE, responseEntity.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
    }
}
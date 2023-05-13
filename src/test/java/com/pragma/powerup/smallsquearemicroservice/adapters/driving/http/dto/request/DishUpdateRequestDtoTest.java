package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DishUpdateRequestDtoTest {
    @InjectMocks
    private DishUpdateRequestDto dishUpdateRequestDto;

    @Test
    void testGetters() {
        // Arrange
        Integer price = 10000;
        String description = "Test Description Update Request";

        // Act
        DishUpdateRequestDto dishUpdate = new DishUpdateRequestDto(description, price);

        // Assert
        assertEquals(price, dishUpdate.getPrice());
        assertEquals(description, dishUpdate.getDescription());
    }

}
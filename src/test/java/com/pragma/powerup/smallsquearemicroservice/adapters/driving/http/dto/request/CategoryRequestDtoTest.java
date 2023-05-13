package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryRequestDtoTest {
    @InjectMocks
    private CategoryRequestDto categoryRequestDto;

    @Test
    void testGetters() {
        // Arrange
        String name = "Test Category Request";
        String description = "Test Category Request";

        // Act
        CategoryRequestDto category = new CategoryRequestDto(name, description);

        // Assert
        assertEquals(name, category.getName());
        assertEquals(description, category.getDescription());
    }
}
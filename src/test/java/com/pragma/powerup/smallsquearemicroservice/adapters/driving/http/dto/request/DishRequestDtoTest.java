package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DishRequestDtoTest {
    @InjectMocks
    private DishRequestDto dishRequestDto;

    @Test
    void testGetters() {
        // Arrange
        String name = "Test Dish";
        Long idCategory = 1L;
        String description = "Test description";
        Integer price = 10000;
        Long idRestaurant = 1L;
        String urlImage = "http://";

        // Act
        DishRequestDto dish = new DishRequestDto(name, idCategory, description, price, idRestaurant, urlImage);

        // Assert
        assertEquals(name, dish.getName());
        assertEquals(idCategory, dish.getIdCategory());
        assertEquals(description, dish.getDescription());
        assertEquals(price, dish.getPrice());
        assertEquals(idRestaurant, dish.getIdRestaurant());
        assertEquals(urlImage, dish.getUrlImagen());
    }
}
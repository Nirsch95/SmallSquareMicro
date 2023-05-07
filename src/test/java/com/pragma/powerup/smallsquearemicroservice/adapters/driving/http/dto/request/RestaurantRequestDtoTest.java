package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantRequestDtoTest {
    @InjectMocks
    private RestaurantRequestDto restaurantRequestDto;
    @Test
    void testGetters() {
        // Arrange
        String name = "Test Restaurant";
        String address = "123 Test St";
        String phone = "555-555-5555";
        String urlLogo = "http://test.com/logo.png";
        Long idOwner = 1L;
        String dniNumber = "1234567890";

        // Act
        RestaurantRequestDto restaurant = new RestaurantRequestDto(name, address, phone, urlLogo, idOwner, dniNumber);

        // Assert
        assertEquals(name, restaurant.getName());
        assertEquals(address, restaurant.getAddress());
        assertEquals(phone, restaurant.getPhone());
        assertEquals(urlLogo, restaurant.getUrlLogo());
        assertEquals(idOwner, restaurant.getIdOwner());
        assertEquals(dniNumber, restaurant.getDniNumber());
    }
}
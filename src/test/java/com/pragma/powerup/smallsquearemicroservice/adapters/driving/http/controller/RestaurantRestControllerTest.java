package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IRestaurantHandler;
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


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RestaurantRestControllerTest {

    @Mock
    private IRestaurantHandler restaurantHandler;

    @InjectMocks
    private RestaurantRestController restController;

    private RestaurantRequestDto restaurantRequestDto;

    private RestaurantSummaryDto restaurantSummaryDto;

    @BeforeEach
    void setUp() {
        RestaurantRequestDto dto = new RestaurantRequestDto("Las delicias de la 5ta","clle 19 N°19-22",
                "18181818",
                "https://jimdo-storage.freetls.fastly.net/image/9939456/d2e94e18-d535-4d67-87ef-e96f4d1b591f.png?quality=80,90&auto=webp&disable=upscale&width=455.23809523809524&height=239&crop=1:0.525",
                10L, "199191919");
    }

    @Test
    @DisplayName("Given a valid user, when saveUser is called, then a CREATED response is returned")
    void testSaveUser() {
        // Arrange
        Mockito.doNothing().when(restaurantHandler).saveRestaurant(restaurantRequestDto);

        // Act
        ResponseEntity<Map<String, String>> responseEntity = restController.saveRestaurant(restaurantRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(Constants.RESTAURANT_CREATED_MESSAGE, responseEntity.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
    }

    @Test
    void testGetRestaurants_ReturnListOfRestaurantSummaryDto() {

        int page = 1;
        int size = 10;
        List<RestaurantSummaryDto> expectedList = Arrays.asList(new RestaurantSummaryDto("name1", "urlLogo1"),
                new RestaurantSummaryDto("name2", "urlLogo2"));
        Mockito.when(restaurantHandler.getRestaurants(page, size)).thenReturn(expectedList);

        ResponseEntity<List<RestaurantSummaryDto>> response = restController.getRestaurants(page, size);

        Mockito.verify(restaurantHandler, times(1)).getRestaurants(page, size);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedList, response.getBody());
    }

    @Test
    void testAddEmployee_ValidRestaurantIdAndUserDto_EmployeeAdded() {

        Long restaurantId = 1L;
        UserRequestDto userDto = new UserRequestDto(1L, "name", "surname", "12345678", "+12345678",
                new Date(18, 10, 2), "example@example.com", "mypassword", 2L);

        ResponseEntity<Map<String, String>> response = restController.addEmployee(restaurantId, userDto);

        Mockito.verify(restaurantHandler, times(1)).addEmployee(restaurantId, userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Map<String, String> responseBody = Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.EMPLOYEE_ADDED_TO_RESTAURANT);
        assertEquals(responseBody, response.getBody());
    }
}
package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.adapter.RestTemplateAdapter;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserDontHaveThisRestaurantException;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserNotBeAOwnerException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.model.User;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {
    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private JwtInterceptor jwtInterceptor;

    @Mock
    private RestTemplateAdapter restTemplateAdapter;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRestaurant_ValidOwner_SaveRestaurant() {

        User owner = new User(123L, "name", "surname", "12345678", "+12345678",
                new Date(18, 10, 2), "example@example.com", "mypassword", 2L);

        when(jwtInterceptor.getUserId()).thenReturn(123L);
        when(restTemplateAdapter.getUser(owner.getId())).thenReturn(owner);

        Restaurant restaurant = new Restaurant(1L, "name", "address", "phone",
                "http://", 123L, "ownerPhone", Collections.emptySet());

        restaurantUseCase.saveRestaurant(restaurant);

        verify(restaurantPersistencePort, times(1)).saveRestaurant(restaurant);
    }

    @Test
    void testSaveRestaurant_InvalidOwner_ThrowUserNotBeAOwnerException() {

        User owner = new User(123L, "name", "surname", "12345678", "+12345678",
                new Date(18, 10, 2), "example@example.com", "mypassword", 3L);

        when(jwtInterceptor.getUserId()).thenReturn(123L);
        when(restTemplateAdapter.getUser(owner.getId())).thenReturn(owner);

        Restaurant restaurant = new Restaurant(1L, "name", "address", "phone",
                "http://", 123L, "ownerPhone", Collections.emptySet());

        assertThrows(UserNotBeAOwnerException.class, () -> restaurantUseCase.saveRestaurant(restaurant));

        verify(restaurantPersistencePort, never()).saveRestaurant(restaurant);
    }

    @Test
    void testGetRestaurants_ReturnListOfRestaurantSummaryDto() {

        int page = 1;
        int size = 10;
        List<RestaurantSummaryDto> expectedList = Collections.singletonList(new RestaurantSummaryDto("name", "urlLogo"));

        when(restaurantPersistencePort.getRestaurants(page, size)).thenReturn(expectedList);

        List<RestaurantSummaryDto> result = restaurantUseCase.getRestaurants(page, size);

        verify(restaurantPersistencePort, times(1)).getRestaurants(page, size);

        assertEquals(expectedList, result);
    }

    @Test
    void testAddEmployee_RestaurantNotFound_ThrowRestaurantNotFoundException() {
        //Arrange
        Long restaurantId = 1L;
        UserRequestDto userDto = new UserRequestDto();
        when(restaurantPersistencePort.findById(restaurantId)).thenReturn(null);
        //Act & Assert
        assertThrows(RestaurantNotFoundException.class, () -> restaurantUseCase.addEmployee(restaurantId, userDto));
        verify(restaurantPersistencePort, never()).addEmployees(any());
    }

    @Test
    void testAddEmployee_ValidInput_AddEmployeeToRestaurant() {

        //Arrange
        Long restaurantId = 1L;
        UserRequestDto userDto = new UserRequestDto();
        Restaurant restaurant = new Restaurant(restaurantId, "name", "str 123", "13245678",
                "http://",123L, "12345678", new HashSet<>());
        UserRequestDto createdEmployee = new UserRequestDto();

        when(jwtInterceptor.getUserId()).thenReturn(123L);
        when(restaurantPersistencePort.findById(restaurantId)).thenReturn(restaurant);
        when(restTemplateAdapter.getUserByDni(userDto.getDniNumber())).thenReturn(createdEmployee);

        //Act
        restaurantUseCase.addEmployee(restaurantId, userDto);

        //Assert
        verify(restaurantPersistencePort, times(1)).addEmployees(restaurant);
        verify(restTemplateAdapter, times(1)).createEmployee(userDto);
    }

    @Test
    void testValidateIdOwner_InvalidOwner_ThrowUserDontHaveThisRestaurantException() {

        Restaurant restaurant = new Restaurant();
        restaurant.setIdOwner(123L);

        when(jwtInterceptor.getUserId()).thenReturn(456L);

        assertThrows(UserDontHaveThisRestaurantException.class, () -> restaurantUseCase.validateIdOwner(restaurant));
    }
}
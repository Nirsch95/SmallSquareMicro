package com.pragma.powerup.smallsquearemicroservice.domain.usecase;


import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserDontHaveThisRestaurantException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Category;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishUseCaseTest {
    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private JwtInterceptor jwtInterceptor;

    private DishUseCase dishUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dishUseCase = new DishUseCase(dishPersistencePort, restaurantPersistencePort, jwtInterceptor);
    }

    @Test
    void testSaveDish_ValidDish_CallsPersistencePortSaveDish() {
        Restaurant restaurant = new Restaurant(1L, "name", "address", "+12345678",
                "urlLogo", 123L, "12345678",new HashSet<>());
        Dish dish = new Dish(1L, "name", new Category(),"description","1234",restaurant,
                "ImageNet.jpg",true);

        when(jwtInterceptor.getUserId()).thenReturn(123L);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(restaurant);

        dishUseCase.saveDish(dish);

        verify(dishPersistencePort, times(1)).saveDish(dish);
    }

    @Test
    void testSaveDish_InvalidRestaurant_ThrowsRestaurantNotFoundException() {
        Dish dish = new Dish();
        dish.setRestaurant(new Restaurant());

        when(jwtInterceptor.getUserId()).thenReturn(123L);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(null);

        assertThrows(RestaurantNotFoundException.class, () -> {
            dishUseCase.saveDish(dish);
        });

        verify(dishPersistencePort, never()).saveDish(any(Dish.class));
    }

    @Test
    void testSaveDish_UserNotOwner_ThrowsUserDontHaveThisRestaurantException() {
        Restaurant restaurant = new Restaurant(1L, "name", "address", "+12345678",
                "urlLogo", 124L, "12345678",new HashSet<>());
        Dish dish = new Dish(1L, "name", new Category(),"description","1234",restaurant,
                "ImageNet.jpg",true);

        when(jwtInterceptor.getUserId()).thenReturn(123L);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(restaurant);

        assertThrows(UserDontHaveThisRestaurantException.class, () -> {
            dishUseCase.saveDish(dish);
        });

        verify(dishPersistencePort, never()).saveDish(any(Dish.class));
    }

    @Test
    void testUpdateDish_ValidDish_CallsPersistencePortUpdateDish() {
        Long dishId = 1L;
        Restaurant restaurant = new Restaurant(1L, "name", "address", "+12345678",
                "urlLogo", 123L, "12345678",new HashSet<>());
        Dish existingDish = new Dish(1L, "name", new Category(),"description","1234",restaurant,
                "ImageNet.jpg",true);

        Dish updatedDish = new Dish();
        updatedDish.setName("Updated Dish");

        when(dishPersistencePort.findById(dishId)).thenReturn(existingDish);
        when(restaurantPersistencePort.findById(restaurant.getId())).thenReturn(restaurant);
        when(jwtInterceptor.getUserId()).thenReturn(123L);

        dishUseCase.updateDish(dishId, updatedDish);

        verify(dishPersistencePort, times(1)).updateDish(dishId, updatedDish);
    }

    @Test
    void testChangeStateDish_ValidDish_CallsPersistencePortSaveDish(){
        // Arrange
        Restaurant restaurant = new Restaurant(1L, "name", "address", "+12345678",
                "urlLogo", 123L, "12345678",new HashSet<>());
        Dish dish = new Dish(1L, "name", new Category(),"description","1234",restaurant,
                "ImageNet.jpg",true);

        when(dishPersistencePort.findById(dish.getId())).thenReturn(dish);
        when(restaurantPersistencePort.findById(restaurant.getId())).thenReturn(restaurant);
        when(jwtInterceptor.getUserId()).thenReturn(123L);

        // Act
        dishUseCase.changeStateDish(dish.getId());

        // Assert
        verify(dishPersistencePort, times(1)).changeStateDish(dish.getId(), dish);
    }

    @Test
    void testGetDishes() {
        // Arrange
        Long idRestaurant = 1L;
        int page = 1;
        int size = 10;
        Long category = 2L;

        List<Dish> expectedDishes = new ArrayList<>();
        expectedDishes.add(new Dish(1L, "Plato 1", new Category(category, "category", "category"),
                "Description 1", "15000", new Restaurant(), "urlImage", true));
        expectedDishes.add(new Dish(2L, "Plato 2", new Category(category, "category", "category"),
                "Description 2", "20000", new Restaurant(), "urlImage", true));

        when(dishPersistencePort.getDishes(idRestaurant, page, size, category)).thenReturn(expectedDishes);

        // Act
        List<Dish> actualDishes = dishUseCase.getDishes(idRestaurant, page, size, category);

        // Assert
        assertEquals(expectedDishes, actualDishes);
        verify(dishPersistencePort, times(1)).getDishes(idRestaurant, page, size, category);
    }
}
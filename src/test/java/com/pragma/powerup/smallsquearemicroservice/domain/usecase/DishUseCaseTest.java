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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishUseCaseTest {
    private IDishPersistencePort dishPersistencePort;
    private IRestaurantPersistencePort restaurantPersistencePort;
    private DishUseCase dishUseCase;

    @BeforeEach
    void setUp() {
        dishPersistencePort = mock(IDishPersistencePort.class);
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        dishUseCase = new DishUseCase(dishPersistencePort, restaurantPersistencePort);
    }

    @Test
    @DisplayName("Given a new Dish with a valid owner, when saveDish is called, then it should save the dish in the repository")
    void saveDish_WhenNewDishWithValidOwner_ShouldSaveDishInRepository() {
        // Arrange
        Dish dish = new Dish(1L, "Name", new Category(), "Description", "15000",
                new Restaurant(1L, "Name", "Address", "56165", "urlLogo.jpg", 2L, "1235156"), "url", true);
        Restaurant restaurant = new Restaurant(1L, "Name", "Address", "56165", "urlLogo.jpg", 2L, "1235156");
        JwtInterceptor.setUserId(2L);
        restaurant.setIdOwner(JwtInterceptor.getUserId());
        when(restaurantPersistencePort.findById(dish.getRestaurant().getId())).thenReturn(restaurant);

        // Act
        dishUseCase.saveDish(dish);

        // Assert
        verify(restaurantPersistencePort,atLeastOnce()).findById(dish.getRestaurant().getId());
        verify(dishPersistencePort).saveDish(dish);
    }

    @Test
    @DisplayName("Given a new Dish with an invalid owner, when saveDish is called, then it should throw UserDontHaveThisRestaurantException")
    void saveDish_WhenNewDishWithInvalidOwner_ShouldThrowUserDontHaveThisRestaurantException() {
        // Arrange
        Dish dish = new Dish(1L, "Name", new Category(), "Description", "15000",
                new Restaurant(1L,"Name","Address","56165","urlLogo.jpg",8L,
                        "1235156"), "url", true);
        Restaurant restaurant = new Restaurant();
        JwtInterceptor.setUserId(2L);
        restaurant.setIdOwner(JwtInterceptor.getUserId() + 1);
        when(restaurantPersistencePort.findById(dish.getRestaurant().getId())).thenReturn(restaurant);

        // Act & Assert
        assertThrows(UserDontHaveThisRestaurantException.class, () -> dishUseCase.saveDish(dish));
    }

    @Test
    @DisplayName("Given a new Dish with a non-existent restaurant, when saveDish is called, then it should throw RestaurantNotFoundException")
    void saveDish_WhenNewDishWithNonExistentRestaurant_ShouldThrowRestaurantNotFoundException() {
        // Arrange
        Dish dish = new Dish(1L, "Name", new Category(), "Description", "15000",
                new Restaurant(1L,"Name","Address","56165","urlLogo.jpg",8L,
                        "1235156"), "url", true);
        when(restaurantPersistencePort.findById(dish.getRestaurant().getId())).thenReturn(null);

        // Act & Assert
        assertThrows(RestaurantNotFoundException.class, () -> dishUseCase.saveDish(dish));
    }

    @Test
    @DisplayName("Given an existing Dish with a valid owner, when updateDish is called, then it should update the dish in the repository")
    void updateDish_WhenExistingDishWithValidOwner_ShouldUpdateDishInRepository() {
        // Arrange
        Long dishId = 1L;
        Dish existingDish = new Dish(dishId, "Existing Dish", new Category(), "Existing Description", "15000",
                new Restaurant(1L, "Name", "Address", "56165", "urlLogo.jpg", 8L, "1235156"), "url", true);
        Dish updatedDish = new Dish(dishId, "Updated Dish", new Category(), "Updated Description", "20000",
                existingDish.getRestaurant(), "updated-url", true);
        JwtInterceptor.setUserId(8L);
        when(dishPersistencePort.findById(dishId)).thenReturn(existingDish);
        when(restaurantPersistencePort.findById(existingDish.getRestaurant().getId())).thenReturn(existingDish.getRestaurant());

        // Act
        dishUseCase.updateDish(dishId, updatedDish);

        // Assert
        verify(dishPersistencePort).findById(dishId);
        verify(restaurantPersistencePort).findById(existingDish.getRestaurant().getId());
        verify(dishPersistencePort).updateDish(dishId, updatedDish);
    }

    @Test
    @DisplayName("Given an existing Dish with a valid owner, when changeStateDish is called, then it should change the dish's state in the repository")
    void changeStateDish_WhenExistingDishWithValidOwner_ShouldChangeDishStateInRepository() {
        // Arrange
        Long dishId = 1L;
        Dish existingDish = new Dish(dishId, "Existing Dish", new Category(), "Existing Description", "15000",
                new Restaurant(1L, "Name", "Address", "56165", "urlLogo.jpg", 8L, "1235156"), "url", true);
        JwtInterceptor.setUserId(8L);
        when(dishPersistencePort.findById(dishId)).thenReturn(existingDish);
        when(restaurantPersistencePort.findById(existingDish.getRestaurant().getId())).thenReturn(existingDish.getRestaurant());

        // Act
        dishUseCase.changeStateDish(dishId);

        // Assert
        verify(dishPersistencePort).findById(dishId);
        verify(restaurantPersistencePort).findById(existingDish.getRestaurant().getId());
        verify(dishPersistencePort).changeStateDish(dishId, existingDish);
        assertFalse(existingDish.getActive());
    }
}
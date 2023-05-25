package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserDontHaveThisRestaurantException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Category;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DishUseCaseTest {
    private IDishPersistencePort dishPersistencePort;
    private IRestaurantRepository restaurantRepository;
    private IDishRepository dishRepository;
    private DishUseCase dishUseCase;

    @BeforeEach
    void setUp() {
        dishPersistencePort = mock(IDishPersistencePort.class);
        restaurantRepository = mock(IRestaurantRepository.class);
        dishRepository = mock(IDishRepository.class);
        dishUseCase = new DishUseCase(dishPersistencePort, restaurantRepository, dishRepository);
    }

    @Test
    @DisplayName("Given a new Dish with a valid owner, when saveDish is called, then it should save the dish in the repository")
    void saveDish_WhenNewDishWithValidOwner_ShouldSaveDishInRepository() {
        // Arrange
        Dish dish = new Dish(1L, "Name", new Category(), "Description", "15000",
                new Restaurant(1L, "Name", "Address", "56165", "urlLogo.jpg", 2L, "1235156"), "url", true);
        RestaurantEntity restaurantEntity = new RestaurantEntity(1L, "Name", "Address", "56165", "urlLogo.jpg", 2L, "1235156");
        JwtInterceptor.setUserId(2L);
        restaurantEntity.setIdOwner(JwtInterceptor.getUserId());
        when(restaurantRepository.findById(dish.getRestaurant().getId())).thenReturn(Optional.of(restaurantEntity));

        // Act
        dishUseCase.saveDish(dish);

        // Assert
        verify(restaurantRepository,atLeastOnce()).findById(dish.getRestaurant().getId());
        verify(dishPersistencePort).saveDish(dish);
    }

    @Test
    @DisplayName("Given a new Dish with an invalid owner, when saveDish is called, then it should throw UserDontHaveThisRestaurantException")
    void saveDish_WhenNewDishWithInvalidOwner_ShouldThrowUserDontHaveThisRestaurantException() {
        // Arrange
        Dish dish = new Dish(1L, "Name", new Category(), "Description", "15000",
                new Restaurant(1L,"Name","Address","56165","urlLogo.jpg",8L,
                        "1235156"), "url", true);
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        JwtInterceptor.setUserId(2L);
        restaurantEntity.setIdOwner(JwtInterceptor.getUserId() + 1);
        when(restaurantRepository.findById(dish.getRestaurant().getId())).thenReturn(Optional.of(restaurantEntity));

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
        when(restaurantRepository.findById(dish.getRestaurant().getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RestaurantNotFoundException.class, () -> dishUseCase.saveDish(dish));
    }

    @Test
    @DisplayName("Given an existing Dish with a valid owner, when updateDish is called, then it should update the dish in the repository")
    void updateDish_WhenExistingDishWithValidOwner_ShouldUpdateDishInRepository() {
        // Arrange
        Long dishId = 1L;
        Dish updatedDish = new Dish(dishId, "Updated Name", new Category(), "Updated Description", "20000",
                new Restaurant(1L, "Name", "Address", "56165", "urlLogo.jpg", 8L, "1235156"), "url", true);
        DishEntity dishEntity = new DishEntity(dishId, "Name", new CategoryEntity(), "Description", "15000",
                new RestaurantEntity(1L, "Name", "Address", "56165", "urlLogo.jpg", 8L, "1235156"), "url", true);
        Optional<DishEntity> dishEntityOptional = Optional.of(dishEntity);
        JwtInterceptor.setUserId(8L);
        dishEntity.getRestaurantEntity().setIdOwner(JwtInterceptor.getUserId());
        when(dishRepository.findById(dishId)).thenReturn(dishEntityOptional);
        when(restaurantRepository.findById(updatedDish.getRestaurant().getId())).thenReturn(Optional.of(dishEntity.getRestaurantEntity()));

        // Act
        dishUseCase.updateDish(dishId, updatedDish);

        // Assert
        verify(dishRepository).findById(dishId);
        verify(restaurantRepository, atLeastOnce()).findById(updatedDish.getRestaurant().getId());
        verify(dishPersistencePort).updateDish(dishId, updatedDish);
    }
}
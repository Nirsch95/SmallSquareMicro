package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantAlreadyExistsException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RestaurantMysqlAdapterTest {

    private RestaurantMysqlAdapter adapter;

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private IRestaurantEntityMapper restaurantEntityMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        adapter = new RestaurantMysqlAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Test
    void saveRestaurant_shouldSaveIfNotExists() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setDniNumber("123456789");
        when(restaurantRepository.findByDniNumber("123456789")).thenReturn(Optional.empty());

        // Act
        adapter.saveRestaurant(restaurant);

        // Assert
        verify(restaurantRepository).save(any());
    }

    @Test
    void saveRestaurant_shouldThrowExceptionIfExists() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setDniNumber("123456789");
        when(restaurantRepository.findByDniNumber("123456789")).thenReturn(Optional.of(new RestaurantEntity()));

        // Act & Assert
        Assertions.assertThrows(RestaurantAlreadyExistsException.class, () -> {
            adapter.saveRestaurant(restaurant);
        });
    }
}
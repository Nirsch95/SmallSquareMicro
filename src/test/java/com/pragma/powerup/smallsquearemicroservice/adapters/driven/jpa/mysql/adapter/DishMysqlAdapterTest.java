package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.DishAlreadyExistsException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

class DishMysqlAdapterTest {
    @Mock
    private IDishRepository dishRepository;

    @Mock
    private IDishEntityMapper dishEntityMapper;

    @InjectMocks
    private DishMysqlAdapter dishMysqlAdapter;

    private IDishPersistencePort dishPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishPersistencePort = new DishMysqlAdapter(dishRepository, dishEntityMapper);
    }

    @Test
    void saveDish_ShouldThrowDishAlreadyExistsException_WhenDishAlreadyExists() {
        // Arrange
        Dish dish = new Dish();
        dish.setName("Pizza Margherita");

        DishEntity existingDishEntity = new DishEntity();
        existingDishEntity.setName("Pizza Margherita");

        Mockito.when(dishRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(existingDishEntity));

        // Act and assert
        assertThrows(DishAlreadyExistsException.class, () -> {
            dishMysqlAdapter.saveDish(dish);
        });

        Mockito.verify(dishRepository, Mockito.times(1))
                .findByName(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(dishRepository);
        Mockito.verifyNoInteractions(dishEntityMapper);
    }

    @Test
    void saveDish_DishAlreadyExists() {
        // Arrange
        Dish dish = new Dish();
        dish.setName("Pizza Margarita");
        dish.setDescription("Delicious pizza with tomato sauce and mozzarella cheese");
        dish.setPrice("1000");
        when(dishRepository.findByName(dish.getName())).thenReturn(Optional.of(new DishEntity()));

        // Act
        Assertions.assertThrows(DishAlreadyExistsException.class, () -> {
            dishMysqlAdapter.saveDish(dish);
        });

        // Assert
        verify(dishRepository, never()).save(any(DishEntity.class));
    }
}
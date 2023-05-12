package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.mockito.Mockito.*;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Category;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DishUseCaseTest {

    private DishUseCase dishUseCase;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        dishUseCase = new DishUseCase(dishPersistencePort);
    }

    @Test
    void saveDish_shouldSaveDish() {
        // Arrange
        Dish dish = new Dish();
        dish.setName("Test dish");
        dish.setCategory(new Category(1L, "Fast Food", "Fast Food"));
        dish.setDescription("This is a test dish.");
        dish.setPrice("15000");
        dish.setRestaurant(new Restaurant());
        dish.setUrlImagen("http://images.dish.com");

        // Act
        dishUseCase.saveDish(dish);

        // Assert
        verify(dishPersistencePort).saveDish(dish);
        assertTrue(dish.getActive(), "Dish is active");
    }
}
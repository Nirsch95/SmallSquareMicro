package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import static org.mockito.Mockito.*;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Category;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryUseCaseTest {

    private CategoryUseCase categoryUseCase;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
    }

    @Test
    void saveCategory_shouldSaveCategory() {
        // Arrange
        Category category = new Category();
        category.setName("Test category");
        category.setDescription("This is a test category.");

        // Act
        categoryUseCase.saveCategory(category);

        // Assert
        verify(categoryPersistencePort).saveCategory(category);
    }
}
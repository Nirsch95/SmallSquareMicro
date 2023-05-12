package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.CategoryAlreadyExistsException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryMysqlAdapterTest {
    private CategoryMysqlAdapter categoryMysqlAdapter;
    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryMysqlAdapter = new CategoryMysqlAdapter(categoryRepository, categoryEntityMapper);
    }

    @Test
    void saveCategory_ShouldSaveCategory_WhenCategoryDoesNotExist() {
        // Arrange
        Category category = new Category();
        category.setName("Test Category");

        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.empty());

        when(categoryEntityMapper.toEntity(category)).thenAnswer(new Answer<CategoryEntity>() {
            @Override
            public CategoryEntity answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                if (args != null && args.length > 0 && args[0] instanceof Category) {
                    Category category = (Category) args[0];
                    CategoryEntity entity = new CategoryEntity();
                    entity.setName(category.getName());
                    return entity;
                }
                return null;
            }
        });

        // Act
        categoryMysqlAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    void saveCategory_ShouldThrowCategoryAlreadyExistsException_WhenCategoryExists() {
        // Arrange
        Category category = new Category();
        category.setName("Test Category");

        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(new CategoryEntity()));

        // Act and Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryMysqlAdapter.saveCategory(category);
        });
    }
}
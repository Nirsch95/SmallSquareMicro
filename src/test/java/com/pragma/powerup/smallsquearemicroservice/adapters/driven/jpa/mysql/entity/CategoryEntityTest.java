package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryEntityTest {
    @Test
    void testGetters() {
        //Arrange
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Fast Food","Fast Food");

        // Act & Assert
        assertEquals(1L, categoryEntity.getId());
        assertEquals("Fast Food", categoryEntity.getName());
        assertEquals("Fast Food", categoryEntity.getDescription());
    }

    @Test
    void testSetters() {
        //Arrange
        CategoryEntity categoryEntity = new CategoryEntity();

        //Act
        categoryEntity.setId(1L);
        categoryEntity.setName("Fast Food");
        categoryEntity.setDescription("Fast Food");

        //Assert
        assertEquals(1L, categoryEntity.getId());
        assertEquals("Fast Food", categoryEntity.getName());
        assertEquals("Fast Food", categoryEntity.getDescription());
    }
}
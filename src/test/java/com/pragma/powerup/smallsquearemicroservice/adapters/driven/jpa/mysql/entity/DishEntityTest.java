package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishEntityTest {
    @Test
    void testGetters() {
        //Arrange
        DishEntity dishEntity = new DishEntity(1L, "Test dish",
                new CategoryEntity(1L, "Fast Food","Fast Food"),"This is a test dish.",
                "15000", new RestaurantEntity(), "http://images.dish.com",true);

        // Act & Assert
        assertEquals(1L, dishEntity.getId());
        assertEquals("Test dish", dishEntity.getName());
        assertEquals(1L, dishEntity.getCategoryEntity().getId());
        assertEquals("Fast Food", dishEntity.getCategoryEntity().getName());
        assertEquals("Fast Food", dishEntity.getCategoryEntity().getDescription());
        assertEquals("This is a test dish.", dishEntity.getDescription());
        assertEquals("15000", dishEntity.getPrice());
        assertEquals(new RestaurantEntity().getClass(), dishEntity.getRestaurantEntity().getClass());
        assertEquals("http://images.dish.com", dishEntity.getUrlImagen());
        assertEquals(true, dishEntity.getActive());
    }

    @Test
    void testSetters() {
        //Arrange
        DishEntity dishEntity = new DishEntity();

        //Act
        dishEntity.setId(1L);
        dishEntity.setName("Test dish");
        dishEntity.setCategoryEntity(new CategoryEntity(1L, "Fast Food","Fast Food"));
        dishEntity.setDescription("This is a test dish.");
        dishEntity.setPrice("15000");
        dishEntity.setRestaurantEntity(new RestaurantEntity());
        dishEntity.setUrlImagen("http://images.dish.com");
        dishEntity.setActive(true);

        //Assert
        assertEquals(1L, dishEntity.getId());
        assertEquals("Test dish", dishEntity.getName());
        assertEquals(1L, dishEntity.getCategoryEntity().getId());
        assertEquals("Fast Food", dishEntity.getCategoryEntity().getName());
        assertEquals("Fast Food", dishEntity.getCategoryEntity().getDescription());
        assertEquals("This is a test dish.", dishEntity.getDescription());
        assertEquals("15000", dishEntity.getPrice());
        assertEquals(new RestaurantEntity().getClass(), dishEntity.getRestaurantEntity().getClass());
        assertEquals("http://images.dish.com", dishEntity.getUrlImagen());
        assertEquals(true, dishEntity.getActive());
    }
}
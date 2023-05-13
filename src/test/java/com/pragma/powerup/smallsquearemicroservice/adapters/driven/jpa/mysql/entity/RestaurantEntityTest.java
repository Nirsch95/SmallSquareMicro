package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantEntityTest {
    @Test
    void testGetters() {
        //Arrange
        RestaurantEntity restaurantEntity = new RestaurantEntity(10L,"Las delicias de la 5ta",
                "clle 19 N°19-22","18181818",
                "https://jimdo-storage.freetls.fastly.net/image/9939456/d2e94e18-d535-4d67-87ef-e96f4d1b591f.png?quality=80,90&auto=webp&disable=upscale&width=455.23809523809524&height=239&crop=1:0.525",
                1L, "199191919");

        // Act & Assert
        assertEquals(10L, restaurantEntity.getId());
        assertEquals("Las delicias de la 5ta", restaurantEntity.getName());
        assertEquals("clle 19 N°19-22", restaurantEntity.getAddress());
        assertEquals("18181818", restaurantEntity.getPhone());
        assertEquals("https://jimdo-storage.freetls.fastly.net/image/9939456/d2e94e18-d535-4d67-87ef-e96f4d1b591f.png?quality=80,90&auto=webp&disable=upscale&width=455.23809523809524&height=239&crop=1:0.525", restaurantEntity.getUrlLogo());
        assertEquals(1L, restaurantEntity.getIdOwner());
        assertEquals("199191919", restaurantEntity.getDniNumber());
    }

    @Test
    void testSetters() {
        //Arrange
        RestaurantEntity restaurantEntity = new RestaurantEntity();

        //Act
        restaurantEntity.setId(10L);
        restaurantEntity.setName("Las delicias de la 5ta");
        restaurantEntity.setAddress("clle 19 N°19-22");
        restaurantEntity.setPhone("18181818");
        restaurantEntity.setUrlLogo("https://jimdo-storage.freetls.fastly.net/image/9939456/d2e94e18-d535-4d67-87ef-e96f4d1b591f.png?quality=80,90&auto=webp&disable=upscale&width=455.23809");
        restaurantEntity.setIdOwner(1L);
        restaurantEntity.setDniNumber("199191919");

        //Assert
        assertEquals(10L, restaurantEntity.getId());
        assertEquals("Las delicias de la 5ta", restaurantEntity.getName());
        assertEquals("clle 19 N°19-22", restaurantEntity.getAddress());
        assertEquals("18181818", restaurantEntity.getPhone());
        assertEquals("https://jimdo-storage.freetls.fastly.net/image/9939456/d2e94e18-d535-4d67-87ef-e96f4d1b591f.png?quality=80,90&auto=webp&disable=upscale&width=455.23809", restaurantEntity.getUrlLogo());
        assertEquals(1L, restaurantEntity.getIdOwner());
        assertEquals("199191919", restaurantEntity.getDniNumber());
    }
}
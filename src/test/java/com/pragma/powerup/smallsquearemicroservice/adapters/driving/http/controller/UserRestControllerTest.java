package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller;

import com.pragma.powerup.smallsquearemicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {
    @Mock
    private RestTemplate restTemplate;

    private UserRestController userRestController;

    @BeforeEach
    public void setUp() {
        userRestController = new UserRestController(restTemplate);
    }

    @Test
    public void testCreateRestaurant() {
        // Arrange
        Long id = 10L;
        String url = "http://localhost:8090/user/" + id;
        User expectedUser = new User();
        expectedUser.setId(10L);
        expectedUser.setName("Liliana");
        expectedUser.setIdRole(2L);

        Mockito.when(restTemplate.getForObject(url, User.class)).thenReturn(expectedUser);

        // Act
        User result = userRestController.getUser(id);

        // Assert
        assertEquals(expectedUser, result);
        Mockito.verify(restTemplate, times(1)).getForObject(url, User.class);
    }
}
package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.controller.UserRestController;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserNotBeAOwnerException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.model.User;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    @Autowired
    private UserRestController userRestController;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant){
        Long idOwner = restaurant.getIdOwner();
        User user = userRestController.getUser(idOwner);
       if (user.getIdRole() != (Constants.OWNER_ROLE_ID)){
            throw new UserNotBeAOwnerException();
        }
        restaurantPersistencePort.saveRestaurant(restaurant);
    }
}

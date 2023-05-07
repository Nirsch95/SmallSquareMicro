package com.pragma.powerup.smallsquearemicroservice.domain.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;

import java.io.IOException;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
}

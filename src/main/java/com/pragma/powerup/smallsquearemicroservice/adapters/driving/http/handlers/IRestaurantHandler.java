package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);
}

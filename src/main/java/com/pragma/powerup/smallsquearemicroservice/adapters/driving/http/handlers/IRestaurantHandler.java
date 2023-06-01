package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.UserDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;

import java.util.List;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);
    List<RestaurantSummaryDto> getRestaurants(int page, int size);
    void addEmployee(Long restaurantId, UserDto userDto);
}

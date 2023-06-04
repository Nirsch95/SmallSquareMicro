package com.pragma.powerup.smallsquearemicroservice.domain.api;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.UserDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    List<RestaurantSummaryDto> getRestaurants(int page, int size);

    void addEmployee(Long restaurantId, UserDto userDto);
}

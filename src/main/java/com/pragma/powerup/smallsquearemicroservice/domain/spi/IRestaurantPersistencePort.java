package com.pragma.powerup.smallsquearemicroservice.domain.spi;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant findById(Long id);
    List<RestaurantSummaryDto> getRestaurants(int page, int size);
}

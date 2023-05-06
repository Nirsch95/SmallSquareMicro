package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantPersistencePort.saveRestaurant(restaurant);
    }
}

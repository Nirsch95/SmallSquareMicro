package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.adapter.RestTemplateAdapter;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserNotBeAOwnerException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.model.User;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    @Autowired
    private RestTemplateAdapter restTemplateAdapter;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant){
        Long idOwner = restaurant.getIdOwner();
        User user = restTemplateAdapter.getUser(idOwner);
       if (!user.getIdRole().equals(Constants.OWNER_ROLE_ID)){
            throw new UserNotBeAOwnerException();
        }
        restaurantPersistencePort.saveRestaurant(restaurant);
    }
}

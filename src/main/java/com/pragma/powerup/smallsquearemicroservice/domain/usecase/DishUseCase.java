package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserDontHaveThisRestaurantException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;


    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveDish(Dish dish) {
        dish.setActive(true);
        validateIdOwner(dish.getRestaurant().getId());
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public void updateDish(Long id, Dish dish) {
        Dish existDish = dishPersistencePort.findById(id);
        validateIdOwner(existDish.getRestaurant().getId());
        dishPersistencePort.updateDish(id, dish);
    }

    @Override
    public void changeStateDish(Long id) {
        Dish dish = dishPersistencePort.findById(id);
        validateIdOwner(dish.getRestaurant().getId());
        dish.setActive(!dish.getActive());
        dishPersistencePort.changeStateDish(id, dish);
    }

    public void validateIdOwner(Long idRestaurant) {
        Restaurant restaurant = restaurantPersistencePort.findById(idRestaurant);
        if(restaurant == null) {
            throw new RestaurantNotFoundException();
        }
        Long userId = JwtInterceptor.getUserId();
        if (!restaurant.getIdOwner().equals(userId)) {
            throw new UserDontHaveThisRestaurantException();
        }
    }
}

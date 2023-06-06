package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserDontHaveThisRestaurantException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;

import java.util.List;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final JwtInterceptor jwtInterceptor;


    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, JwtInterceptor jwtInterceptor) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.jwtInterceptor = jwtInterceptor;
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

    @Override
    public List<Dish> getDishes(Long idRestaurant, int page, int size, Long categoy) {
        return dishPersistencePort.getDishes(idRestaurant, page, size, categoy);
    }

    public void validateIdOwner(Long idRestaurant) {
        Restaurant restaurant = restaurantPersistencePort.findById(idRestaurant);
        if(restaurant == null) {
            throw new RestaurantNotFoundException();
        }
        Long userId = jwtInterceptor.getUserId();
        if (!restaurant.getIdOwner().equals(userId)) {
            throw new UserDontHaveThisRestaurantException();
        }
    }
}

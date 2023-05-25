package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserDontHaveThisRestaurantException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IDishPersistencePort;

import java.util.Optional;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantRepository restaurantRepository;

    private final IDishRepository dishRepository;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantRepository restaurantRepository, IDishRepository dishRepository) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public void saveDish(Dish dish) {
        dish.setActive(true);
        validateIdOwner(dish.getRestaurant().getId());
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public void updateDish(Long id, Dish dish) {
        Optional<DishEntity> dishEntity = dishRepository.findById(id);
        validateIdOwner(dishEntity.get().getRestaurantEntity().getId());
        dishPersistencePort.updateDish(id, dish);
    }

    public void validateIdOwner(Long idRestaurant) {
        Optional<RestaurantEntity> restaurantEntity;
        if(restaurantRepository.findById(idRestaurant).isEmpty()){
            throw new RestaurantNotFoundException();
        }
        restaurantEntity = restaurantRepository.findById(idRestaurant);
        Long userId = JwtInterceptor.getUserId();

        if (!restaurantEntity.get().getIdOwner().equals(userId)) {
            throw new UserDontHaveThisRestaurantException();
        }
    }
}

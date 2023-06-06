package com.pragma.powerup.smallsquearemicroservice.domain.spi;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;

import java.util.List;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    void updateDish(Long id, Dish dish);
    void changeStateDish(Long id, Dish dish);
    Dish findById(Long id);
    List<Dish> getDishes (Long idRestaurant, int page, int size, Long category);
}

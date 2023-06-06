package com.pragma.powerup.smallsquearemicroservice.domain.api;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;

import java.util.List;

public interface IDishServicePort {
    void saveDish(Dish dish);
    void updateDish(Long id, Dish dish);
    void changeStateDish(Long id);
    List<Dish> getDishes(Long idRestaurant, int page, int size, Long category);
}

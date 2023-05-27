package com.pragma.powerup.smallsquearemicroservice.domain.api;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;

public interface IDishServicePort {
    void saveDish(Dish dish);
    void updateDish(Long id, Dish dish);
    void changeStateDish(Long id);
}

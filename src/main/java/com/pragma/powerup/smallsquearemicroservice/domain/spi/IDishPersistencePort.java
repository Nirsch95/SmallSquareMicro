package com.pragma.powerup.smallsquearemicroservice.domain.spi;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    void updateDish(Long id, Dish dish);
}
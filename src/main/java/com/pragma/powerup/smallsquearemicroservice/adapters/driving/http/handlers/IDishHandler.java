package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishRequestDto;

public interface IDishHandler {
    void saveDish(DishRequestDto dishRequestDto);
    void updateDish(Long id,DishRequestDto dishRequestDto);
    void changeStateDish(Long id);
}

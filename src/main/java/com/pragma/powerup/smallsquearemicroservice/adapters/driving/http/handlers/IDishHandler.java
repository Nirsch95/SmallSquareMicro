package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {
    void saveDish(DishRequestDto dishRequestDto);
    void updateDish(Long id,DishRequestDto dishRequestDto);
    void changeStateDish(Long id);
    List<DishResponseDto> getDishes(Long idRestaurant, int page, int size, Long category);
}

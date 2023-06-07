package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.OrderDishRequestDto;

public interface IOrderDishHandler {
    void saveOrderDish(OrderDishRequestDto orderDishRequestDto);
}

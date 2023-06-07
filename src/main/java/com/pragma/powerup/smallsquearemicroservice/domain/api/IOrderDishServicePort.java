package com.pragma.powerup.smallsquearemicroservice.domain.api;

import com.pragma.powerup.smallsquearemicroservice.domain.model.OrderDish;

public interface IOrderDishServicePort {
    void saveOrderDish(OrderDish orderDish);
}

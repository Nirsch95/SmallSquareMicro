package com.pragma.powerup.smallsquearemicroservice.domain.spi;

import com.pragma.powerup.smallsquearemicroservice.domain.model.OrderDish;

public interface IOrderDishPersistencePort {
    void saveOrderDish(OrderDish orderDish);
}

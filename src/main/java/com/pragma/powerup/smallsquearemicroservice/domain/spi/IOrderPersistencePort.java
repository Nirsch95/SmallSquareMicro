package com.pragma.powerup.smallsquearemicroservice.domain.spi;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Order;

public interface IOrderPersistencePort {
    void saveOrder(Order order);
}

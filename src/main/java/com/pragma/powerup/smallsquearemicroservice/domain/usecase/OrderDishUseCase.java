package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.domain.api.IOrderDishServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.model.OrderDish;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IOrderDishPersistencePort;

public class OrderDishUseCase implements IOrderDishServicePort {
    private final IOrderDishPersistencePort orderDishPersistencePort;

    public OrderDishUseCase(IOrderDishPersistencePort orderDishPersistencePort) {
        this.orderDishPersistencePort = orderDishPersistencePort;
    }

    @Override
    public void saveOrderDish(OrderDish orderDish) {
        orderDishPersistencePort.saveOrderDish(orderDish);
    }
}

package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Order;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;

import java.util.Date;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final JwtInterceptor jwtInterceptor;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, JwtInterceptor jwtInterceptor) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void saveOrder(Long idRestaurant) {
        Restaurant restaurant = restaurantPersistencePort.findById(idRestaurant);
        Order order = new Order();
        order.setIdClient(jwtInterceptor.getUserId());
        order.setCreated(new Date());
        order.setState("Pending");
        order.setRestaurant(restaurant);
        orderPersistencePort.saveOrder(order);
    }
}

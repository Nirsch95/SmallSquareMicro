package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.DishDoesntExistsInRestaurantException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers.IOrderDishEntityMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IOrderDishRepository;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.pragma.powerup.smallsquearemicroservice.domain.model.OrderDish;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IOrderDishPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class OrderDishMysqlAdapter implements IOrderDishPersistencePort {
    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;
    private final IOrderRepository orderRepository;
    private final IDishRepository dishRepository;

    @Override
    public void saveOrderDish(OrderDish orderDish) {

        if(!orderDish.getOrder().getRestaurant().equals(orderDish.getDish().getRestaurant())){
            throw new DishDoesntExistsInRestaurantException();
        }
        orderDishRepository.save(orderDishEntityMapper.toEntity(orderDish));
    }
}

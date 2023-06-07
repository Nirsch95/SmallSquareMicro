package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.OrderNotBeCreatedException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers.IOrderEntityMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Order;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class OrderMysqlAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public void saveOrder(Order order) {
        Optional<OrderEntity> orderEntity = orderRepository.findByIdClient(order.getIdClient());
        if(orderEntity.isPresent()){
            if(!Objects.equals(orderEntity.get().getState(), "delivered")){
                throw new OrderNotBeCreatedException();
            }
            orderRepository.save(orderEntityMapper.toEntity(order));
        }
        orderRepository.save(orderEntityMapper.toEntity(order));
    }
}

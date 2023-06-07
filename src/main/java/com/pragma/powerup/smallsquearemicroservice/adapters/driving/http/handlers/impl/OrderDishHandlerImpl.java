package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.OrderDishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IOrderDishHandler;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper.IOrderDishRequestMapper;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IOrderDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDishHandlerImpl implements IOrderDishHandler {
    private final IOrderDishRequestMapper orderDishRequestMapper;
    private final IOrderDishServicePort orderDishServicePort;
    @Override
    public void saveOrderDish(OrderDishRequestDto orderDishRequestDto) {
        orderDishServicePort.saveOrderDish(orderDishRequestMapper.toOrderDish(orderDishRequestDto));
    }
}

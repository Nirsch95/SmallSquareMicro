package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    @Override
    public void saveOrder(Long idRestaurant) {
        orderServicePort.saveOrder(idRestaurant);
    }
}

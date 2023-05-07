package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.smallsquearemicroservice.configuration.BeanConfiguration;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;


    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto){
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequestDto));
    }
}

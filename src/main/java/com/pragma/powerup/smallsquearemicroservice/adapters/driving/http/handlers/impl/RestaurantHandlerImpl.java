package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.UserDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto){
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequestDto));
    }

    @Override
    public List<RestaurantSummaryDto> getRestaurants(int page, int size) {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getRestaurants(page, size));
    }

    @Override
    public void addEmployee(Long restaurantId, UserDto userDto) {
        restaurantServicePort.addEmployee(restaurantId, userDto);
    }
}

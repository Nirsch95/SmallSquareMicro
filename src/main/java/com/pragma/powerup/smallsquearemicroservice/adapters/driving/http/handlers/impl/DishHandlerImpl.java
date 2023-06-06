package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers.IDishHandler;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper.IDishRequestMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper.IDishResponseMapper;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishHandlerImpl implements IDishHandler {
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        dishServicePort.saveDish(dishRequestMapper.toDish(dishRequestDto));
    }

    @Override
    public void updateDish(Long id, DishRequestDto dishRequestDto) {
        dishServicePort.updateDish(id, dishRequestMapper.toDish(dishRequestDto));
    }

    @Override
    public void changeStateDish(Long id) {
        dishServicePort.changeStateDish(id);
    }

    @Override
    public List<DishResponseDto> getDishes(Long idRestaurant, int page, int size, Long category) {
        return dishResponseMapper.toResponseList(dishServicePort.getDishes(idRestaurant, page, size, category));
    }
}

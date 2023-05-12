package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.CategoryRequestDto;

public interface ICategoryHandler {
    void saveCategory(CategoryRequestDto categoryRequestDto);
}

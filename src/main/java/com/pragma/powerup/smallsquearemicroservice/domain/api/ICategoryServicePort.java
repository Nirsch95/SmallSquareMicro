package com.pragma.powerup.smallsquearemicroservice.domain.api;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Category;

public interface ICategoryServicePort {
    void saveCategory(Category category);
}

package com.pragma.powerup.smallsquearemicroservice.domain.spi;

import com.pragma.powerup.smallsquearemicroservice.domain.model.Category;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
}

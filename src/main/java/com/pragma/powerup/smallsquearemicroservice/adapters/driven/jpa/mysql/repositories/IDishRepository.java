package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    boolean existsByNameAndRestaurantEntityId(String name, Long id);
}

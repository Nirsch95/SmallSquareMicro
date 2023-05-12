package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findByName(String name);
    List<DishEntity> findAllById(Long idRestaurant);
}

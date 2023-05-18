package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.DishAlreadyExistsException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.DishNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
public class DishMysqlAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void saveDish(Dish dish) {
        List<DishEntity> dishes = dishRepository.findAllByRestaurantEntityId(dish.getRestaurant().getId());
        dishes.forEach(d -> {
            if(d.getName().equals(dish.getName())){
                throw new DishAlreadyExistsException();
            }
        });
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public void updateDish(Long id, Dish dish) {
        DishEntity existingDishEntity = dishRepository.findById(id)
                .orElseThrow(DishNotFoundException::new);

        existingDishEntity.setDescription(dish.getDescription());
        existingDishEntity.setPrice(dish.getPrice());

        dishRepository.save(existingDishEntity);
    }
}

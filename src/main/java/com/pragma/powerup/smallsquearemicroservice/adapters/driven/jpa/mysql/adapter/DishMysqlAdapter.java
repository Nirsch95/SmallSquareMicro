package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.DishAlreadyExistsException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.DishNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Category;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class DishMysqlAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void saveDish(Dish dish) {
        boolean dishExists = dishRepository.existsByNameAndRestaurantEntityId(dish.getName(), dish.getRestaurant().getId());
        if (dishExists) {
            throw new DishAlreadyExistsException();
        }
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

    @Override
    public void changeStateDish(Long id, Dish dish) {
        DishEntity existingDishEntity = dishRepository.findById(id)
                .orElseThrow(DishNotFoundException::new);
        existingDishEntity.setActive(!existingDishEntity.getActive());
        dishRepository.save(existingDishEntity);
    }

    @Override
    public Dish findById(Long id) {
        Optional<DishEntity> dishEntity = dishRepository.findById(id);
        if(dishEntity.isPresent()){
            DishEntity entity = dishEntity.get();
            RestaurantEntity restaurant = entity.getRestaurantEntity();
            CategoryEntity category = entity.getCategoryEntity();
            return new Dish(entity.getId(), entity.getName(), new Category(category.getId(), category.getName(),
                    category.getDescription()), entity.getDescription(), entity.getPrice(),
                    new Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getAddress(),
                            restaurant.getPhone(), restaurant.getUrlLogo(), restaurant.getIdOwner(),
                            restaurant.getDniNumber(), restaurant.getEmployees()), entity.getUrlImagen(), entity.getActive());
        }
    throw new DishNotFoundException();
    }
}

package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.InvalidPageException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantAlreadyExistsException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class RestaurantMysqlAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        if (restaurantRepository.findByDniNumber(restaurant.getDniNumber()).isPresent()) {
            throw new RestaurantAlreadyExistsException();
        }
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public Restaurant findById(Long id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        if(restaurantEntity.isPresent()) {
            RestaurantEntity restaurant = restaurantEntity.get();
            return new Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getAddress(),
                    restaurant.getPhone(), restaurant.getUrlLogo(), restaurant.getIdOwner(), restaurant.getDniNumber(),
                    restaurant.getEmployees());
        }
        throw new RestaurantNotFoundException();
    }

    @Override
    public List<RestaurantSummaryDto> getRestaurants(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("name").ascending());

        Page<RestaurantEntity> restaurantPage = restaurantRepository.findAll(pageRequest);
        if (restaurantPage.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<RestaurantEntity> restaurantEntityList = restaurantPage.getContent();

        int totalPages = restaurantPage.getTotalPages();

        if (page < 1 || page >= totalPages) {
            throw new InvalidPageException();
        }

        return restaurantEntityMapper.toRestaurantSummaryList(restaurantEntityList);
    }

    @Override
    public void addEmployees(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }
}

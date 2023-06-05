package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.adapter.RestTemplateAdapter;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserDontHaveThisRestaurantException;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserNotBeAOwnerException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.model.User;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final JwtInterceptor jwtInterceptor;
    private final RestTemplateAdapter restTemplateAdapter;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, JwtInterceptor jwtInterceptor, RestTemplateAdapter restTemplateAdapter) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.jwtInterceptor = jwtInterceptor;
        this.restTemplateAdapter = restTemplateAdapter;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        Long idOwner = restaurant.getIdOwner();
        User user = restTemplateAdapter.getUser(idOwner);
        if (!user.getIdRole().equals(Constants.OWNER_ROLE_ID)) {
            throw new UserNotBeAOwnerException();
        }
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public List<RestaurantSummaryDto> getRestaurants(int page, int size) {
        return restaurantPersistencePort.getRestaurants(page, size);
    }

    @Override
    public void addEmployee(Long restaurantId, UserRequestDto userDto) {
        Restaurant restaurant = restaurantPersistencePort.findById(restaurantId);
        if(restaurant==null) {
            throw new RestaurantNotFoundException();
        }
        validateIdOwner(restaurant);
        restTemplateAdapter.createEmployee(userDto);
        UserRequestDto employee = restTemplateAdapter.getUserByDni(userDto.getDniNumber());
        restaurant.addEmployeeId(employee.getId());
        restaurantPersistencePort.addEmployees(restaurant);
    }

    public void validateIdOwner(Restaurant restaurant) {
        Long userId = jwtInterceptor.getUserId();
        if (!restaurant.getIdOwner().equals(userId)) {
            throw new UserDontHaveThisRestaurantException();
        }
    }
}

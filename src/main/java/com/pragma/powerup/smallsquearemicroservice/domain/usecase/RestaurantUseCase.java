package com.pragma.powerup.smallsquearemicroservice.domain.usecase;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.adapter.RestTemplateAdapter;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.UserDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import com.pragma.powerup.smallsquearemicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserNotBeAOwnerException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import com.pragma.powerup.smallsquearemicroservice.domain.model.User;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    @Autowired
    private RestTemplateAdapter restTemplateAdapter;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant){
        Long idOwner = restaurant.getIdOwner();
        User user = restTemplateAdapter.getUser(idOwner);
       if (!user.getIdRole().equals(Constants.OWNER_ROLE_ID)){
            throw new UserNotBeAOwnerException();
        }
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public List<RestaurantSummaryDto> getRestaurants(int page, int size) {
        return restaurantPersistencePort.getRestaurants(page, size);
    }

    @Override
    public void addEmployee(Long restaurantId, UserDto userDto) {
        Restaurant restaurant = restaurantPersistencePort.findById(restaurantId);
        String dniUser = restTemplateAdapter.createEmployee(userDto);
        User employee = restTemplateAdapter.getUserByDni(dniUser);
        restaurant.getEmployeesIds().add(employee.getId());
        restaurantPersistencePort.saveRestaurant(restaurant);
    }
}

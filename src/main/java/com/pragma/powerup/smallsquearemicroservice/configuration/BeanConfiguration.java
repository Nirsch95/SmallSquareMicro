package com.pragma.powerup.smallsquearemicroservice.configuration;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.adapter.*;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers.*;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.repositories.*;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.adapter.RestTemplateAdapter;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.api.*;
import com.pragma.powerup.smallsquearemicroservice.domain.spi.*;
import com.pragma.powerup.smallsquearemicroservice.domain.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    private final JwtInterceptor jwtInterceptor;
    private final RestTemplateAdapter restTemplateAdapter;
    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), jwtInterceptor, restTemplateAdapter);
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantMysqlAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort(), restaurantPersistencePort(), jwtInterceptor);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishMysqlAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryMysqlAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort(), restaurantPersistencePort(), jwtInterceptor);
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderMysqlAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderDishServicePort orderDishServicePort(){
        return new OrderDishUseCase(orderDishPersistencePort());
    }

    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort(){
        return new OrderDishMysqlAdapter(orderDishRepository, orderDishEntityMapper);
    }
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

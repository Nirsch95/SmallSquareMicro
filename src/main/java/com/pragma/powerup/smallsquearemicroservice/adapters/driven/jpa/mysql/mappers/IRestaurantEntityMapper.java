package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurant);
    List<Restaurant> toRestaurantList(List<RestaurantEntity> restaurantEntityList);
    List<RestaurantSummaryDto> toRestaurantSummaryList(List<RestaurantEntity> restaurantEntityList);
}

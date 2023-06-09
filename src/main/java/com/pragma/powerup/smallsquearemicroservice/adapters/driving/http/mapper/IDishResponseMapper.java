package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {
    @Mapping(target = "idCategory", source = "category.id")
    @Mapping(target = "idRestaurant", source = "restaurant.id")
    DishResponseDto toResponse(Dish dish);
    @Mapping(target = "idCategory", source = "dish.category.id")
    @Mapping(target = "idRestaurant", source = "dish.restaurant.id")
    List<DishResponseDto> toResponseList(List<Dish> dishList);
}

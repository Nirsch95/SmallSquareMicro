package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {
    @Mapping(target = "category.id", source="idCategory")
    @Mapping(target = "restaurant.id", source="idRestaurant")
    Dish toDish(DishRequestDto dishRequestDto);
}

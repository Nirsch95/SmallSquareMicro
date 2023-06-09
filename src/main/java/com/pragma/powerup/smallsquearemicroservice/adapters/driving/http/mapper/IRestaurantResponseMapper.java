package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.response.RestaurantSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
    List<RestaurantSummaryDto> toResponseList(List<RestaurantSummaryDto> restaurantList);
}

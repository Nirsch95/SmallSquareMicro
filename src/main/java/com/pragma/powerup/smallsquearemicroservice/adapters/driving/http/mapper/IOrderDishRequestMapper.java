package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.OrderDishRequestDto;
import com.pragma.powerup.smallsquearemicroservice.domain.model.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishRequestMapper {
    @Mapping(target = "order.id", source = "idOrder")
    @Mapping(target = "dish.id", source = "idDish")
    OrderDish toOrderDish(OrderDishRequestDto orderDishRequestDto);
}

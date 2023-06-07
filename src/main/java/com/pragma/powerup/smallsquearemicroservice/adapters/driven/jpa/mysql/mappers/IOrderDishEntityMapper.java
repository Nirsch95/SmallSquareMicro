package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.powerup.smallsquearemicroservice.domain.model.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishEntityMapper {
    @Mapping(target = "orderEntity.id", source = "order.id")
    @Mapping(target = "dishEntity.id", source = "dish.id")
    OrderDishEntity toEntity(OrderDish orderDish);
}

package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
    @Mapping(target = "restaurantEntity.id", source = "restaurant.id")
    OrderEntity toEntity(Order order);
    List<Order> toOrderList(List<OrderEntity> orderEntityList);
}

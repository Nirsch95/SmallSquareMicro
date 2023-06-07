package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDishRequestDto {
    private Long idOrder;
    private Long idDish;
    private int quantity;
}

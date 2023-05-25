package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishUpdateRequestDto {
    private String description;
    private Integer price;
}

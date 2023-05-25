package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
}

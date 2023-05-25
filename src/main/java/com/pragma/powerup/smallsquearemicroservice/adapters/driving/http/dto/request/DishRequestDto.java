package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishRequestDto {
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String name;
    @NotNull(message = Constants.EMPTY_FIELD_MESSAGE)
    private Long idCategory;
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String description;
    @NotNull(message = Constants.EMPTY_FIELD_MESSAGE)
    @Min(value = 1, message = "")
    private Integer price;
    @NotNull(message = Constants.EMPTY_FIELD_MESSAGE)
    private Long idRestaurant;
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String urlImagen;
}

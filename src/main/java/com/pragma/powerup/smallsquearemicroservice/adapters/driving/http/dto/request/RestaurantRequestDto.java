package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import com.pragma.powerup.smallsquearemicroservice.configuration.Constants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantRequestDto {
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    @Pattern(regexp = "^(?=.*[a-zA-Z\s])[a-zA-Z\s0-9]+$", message = Constants.INVALID_FORMAT_NAME_MESSAGE)
    private String name;
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String address;
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    @Pattern(regexp = "^\\+?[0-9]{12}$", message = Constants.INVALID_FORMAT_PHONE_MESSAGE)
    private String phone;
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String urlLogo;
    @NotNull(message = Constants.EMPTY_FIELD_MESSAGE)
    private Long idOwner;
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    @Pattern(regexp = "^[0-9]{1,20}$", message = Constants.INVALID_VALUE_DNI_NUMBER)
    private String dniNumber;
}

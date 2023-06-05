package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private Long id;
    private String name;
    private String surname;
    private String dniNumber;
    private String phone;
    private Date birthdate;
    private String mail;
    private String password;
    @JsonIgnore
    private Long idRole;
}

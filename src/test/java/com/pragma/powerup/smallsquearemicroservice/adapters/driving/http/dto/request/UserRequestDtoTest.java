package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRequestDtoTest {
    @InjectMocks
    private UserRequestDto userRequestDto;
    @Test
    void test() {
        // Arrange
        Long id = 1L;
        String name = "name";
        String surname = "surname";
        String dniNumber = "12345678";
        String phone = "+12345678";
        Date birthdate = new Date(18,10,2);
        String mail = "example@example.com";
        String password = "mypassword";
        Long idRole = 2L;

        // Act
        UserRequestDto user = new UserRequestDto();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setDniNumber(dniNumber);
        user.setPhone(phone);
        user.setBirthdate(birthdate);
        user.setMail(mail);
        user.setPassword(password);
        user.setIdRole(idRole);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(dniNumber, user.getDniNumber());
        assertEquals(phone, user.getPhone());
        assertEquals(birthdate, user.getBirthdate());
        assertEquals(mail, user.getMail());
        assertEquals(password, user.getPassword());
        assertEquals(idRole, user.getIdRole());
    }
}
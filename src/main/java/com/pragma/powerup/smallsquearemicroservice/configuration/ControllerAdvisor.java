package com.pragma.powerup.smallsquearemicroservice.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions.ForbiddenCustomException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions.UserNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions.UserNotFoundMicroserviceDownException;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.exception.InvalidTokenException;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.exception.UserIsNotAllowedException;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserDontHaveThisRestaurantException;
import com.pragma.powerup.smallsquearemicroservice.domain.exceptions.UserNotBeAOwnerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.pragma.powerup.smallsquearemicroservice.configuration.Constants.*;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_DATA_FOUND_MESSAGE));
    }

    @ExceptionHandler(RestaurantAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
            RestaurantAlreadyExistsException restaurantAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, RESTAURANT_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotFoundException(
            RestaurantNotFoundException restaurantNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, RESTAURANT_NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(UserNotBeAOwnerException.class)
    public ResponseEntity<Map<String, String>> handleUserNotBeAOwnerException(
            UserNotBeAOwnerException userNotBeAOwnerException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_NOT_A_OWNER_MESSAGE));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(UserNotFoundMicroserviceDownException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundMicroserviceDownException(
            UserNotFoundMicroserviceDownException userNotFoundMicroserviceDownException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_NOT_FOUND_MICROSERVER_DOWN_MESSAGE));
    }

    @ExceptionHandler(DishAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleDishAlreadyExistsException(
            DishAlreadyExistsException dishAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, DISH_ALREADY_EXISTS_MESSAGE));
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDishNotFoundException(
            DishNotFoundException dishNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, DISH_NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            CategoryAlreadyExistsException categoryAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CATEGORY_ALREADY_EXISTS_MESSAGE));
    }

    @ExceptionHandler(UserDontHaveThisRestaurantException.class)
    public ResponseEntity<Map<String, String>> handleUserDontHaveThisRestaurantException(
            UserDontHaveThisRestaurantException userDontHaveThisRestaurantException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, INVALID_USER_TO_CREATE_UPDATE_DISH));
    }

    @ExceptionHandler(UserIsNotAllowedException.class)
    public ResponseEntity<Map<String, String>> handleUserIsNotAllowedException(
            UserIsNotAllowedException userIsNotAllowedException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, UNALLOWED_USER_TO_USE_ENDPOINT));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Map<String, String>> handleInvalidTokenException(
            InvalidTokenException invalidTokenException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, INVALID_TOKEN_MESSAGE));
    }

    @ExceptionHandler(InvalidPageException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPageException(
            InvalidPageException invalidPageException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, INVALID_PAGE_MESSAGE));
    }

    @ExceptionHandler(ForbiddenCustomException.class)
    public ResponseEntity<Map<String, String>> handleForbiddenCustomException(
            ForbiddenCustomException forbiddenCustomException) {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorBody = forbiddenCustomException.getMessage();
        try {
            Map<String, String> errorMap = objectMapper.readValue(errorBody, new TypeReference<Map<String, String>>() {});
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMap);
        } catch (IOException e) {
            // Manejar la excepción de deserialización si ocurre algún error
            e.printStackTrace();
            // Devolver un mensaje genérico en caso de que no se pueda deserializar el cuerpo de la respuesta
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, "Error de formato de respuesta"));
        }
    }
}

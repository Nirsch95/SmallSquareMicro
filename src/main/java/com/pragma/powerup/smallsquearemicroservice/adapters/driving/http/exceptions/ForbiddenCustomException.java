package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions;

public class ForbiddenCustomException extends RuntimeException {
    public ForbiddenCustomException(String message){
        super(message);
    }
}

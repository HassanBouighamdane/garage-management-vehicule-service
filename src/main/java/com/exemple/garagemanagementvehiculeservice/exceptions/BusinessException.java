package com.exemple.garagemanagementvehiculeservice.exceptions;

public class BusinessException extends RuntimeException{
    private String message;

    public BusinessException(String message) {
        this.message = message;
    }
}

package com.example.liderit.exceptions_handler.exceptions.param;

public class WrongParameterException extends RuntimeException{
    public WrongParameterException(String message) {
        super(message);
    }
}

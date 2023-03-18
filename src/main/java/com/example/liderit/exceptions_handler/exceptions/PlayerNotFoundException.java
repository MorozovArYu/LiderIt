package com.example.liderit.exceptions_handler.exceptions;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(Integer id) {
        super(String.format("Player with id:%d not found",id));
    }
}

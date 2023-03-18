package com.example.liderit.exceptions_handler.exceptions;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(Integer id) {
        super(String.format("Team with id:%d not found",id));
    }
}

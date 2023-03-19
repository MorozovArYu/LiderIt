package com.example.liderit.exceptions_handler.exceptions.not_found;

public class TeamNotFoundException extends NotFoundException{
    public TeamNotFoundException(Integer id) {
        super(String.format("Team with id:%d not found",id));
    }
}

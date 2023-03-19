package com.example.liderit.exceptions_handler.exceptions.not_found;

public class PlayerNotFoundException extends NotFoundException{
    public PlayerNotFoundException(Integer id) {
        super(String.format("Player with id:%d not found",id));
    }
}

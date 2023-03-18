package com.example.liderit.exceptions_handler.advices;


import com.example.liderit.exceptions_handler.exceptions.PlayerNotFoundException;
import com.example.liderit.exceptions_handler.exceptions.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlayerNotFoundAdvice {
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleValidationException(PlayerNotFoundException ex) {
        return ex.getMessage();
    }

}

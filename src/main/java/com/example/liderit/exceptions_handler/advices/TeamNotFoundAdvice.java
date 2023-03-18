package com.example.liderit.exceptions_handler.advices;


import com.example.liderit.exceptions_handler.exceptions.TeamNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TeamNotFoundAdvice {
    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleValidationException(TeamNotFoundException ex) {
        return ex.getMessage();
    }

}

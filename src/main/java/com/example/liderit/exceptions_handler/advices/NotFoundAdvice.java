package com.example.liderit.exceptions_handler.advices;


import com.example.liderit.exceptions_handler.exceptions.not_found.NotFoundException;
import com.example.liderit.exceptions_handler.exceptions.not_found.TeamNotFoundException;
import com.example.liderit.models.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice("com.example.liderit")
public class NotFoundAdvice {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
        ErrorMessage message = new ErrorMessage();
        message.setDate(LocalDateTime.now());
        message.setMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


}

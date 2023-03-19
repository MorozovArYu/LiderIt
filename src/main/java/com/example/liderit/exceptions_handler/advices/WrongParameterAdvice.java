package com.example.liderit.exceptions_handler.advices;

import com.example.liderit.exceptions_handler.exceptions.param.WrongParameterException;
import com.example.liderit.models.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice("com.example.liderit")
public class WrongParameterAdvice {
    @ExceptionHandler(WrongParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleWrongParameterException(WrongParameterException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(),
                LocalDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }
}

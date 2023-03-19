package com.example.liderit.exceptions_handler.advices;


import com.example.liderit.controllers.TeamController;
import com.example.liderit.exceptions_handler.exceptions.param.WrongParameterException;
import com.example.liderit.models.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice(assignableTypes = TeamController.class)
public class TeamControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleFilteredTeamsByCreationDate(MethodArgumentTypeMismatchException ex, HandlerMethod handlerMethod) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(),
                LocalDateTime.now(),
                ex.getValue() + ": incorrect format. Format must be 'yyyy-MM-dd'")
                , HttpStatus.BAD_REQUEST);
    }



}

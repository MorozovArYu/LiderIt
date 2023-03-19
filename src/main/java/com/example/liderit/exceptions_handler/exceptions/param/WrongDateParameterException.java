package com.example.liderit.exceptions_handler.exceptions.param;

import java.time.LocalDate;
import java.util.Arrays;

public class WrongDateParameterException extends WrongParameterException{
    public WrongDateParameterException(String message, LocalDate... localDates) {
        super(message + " " + Arrays.toString(localDates));
    }
}

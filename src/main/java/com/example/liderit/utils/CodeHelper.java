package com.example.liderit.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class CodeHelper {
    public static <T> ResponseEntity<Collection<T>> checkForEmpty(Collection<T> collection) {
        return collection.isEmpty() ?
                new ResponseEntity<>(collection, HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(collection, HttpStatus.OK);
    }
}

package com.example.tbanklabwork.controller;

import com.example.tbanklabwork.exception.UnSupportedLangException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(UnSupportedLangException.class)
    public ResponseEntity<String> handleUnSupportedException(UnSupportedLangException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceAccessException(ResourceAccessException e) {
        return new ResponseEntity<>("resource is not available", HttpStatus.BAD_REQUEST);
    }
}

package com.example.tbanklabwork.controller;

import com.example.tbanklabwork.exception.UnSupportedLangException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

/**
 * Global exception handler for the application.
 * This class handles specific exceptions and provides appropriate HTTP responses.
 */
@RestControllerAdvice
public class ExceptionsHandler {

    /**
     * Handles UnSupportedLangException and returns a BAD_REQUEST response.
     *
     * @param e the UnSupportedLangException exception
     * @return a ResponseEntity containing the exception message and a BAD_REQUEST status
     */
    @ExceptionHandler(UnSupportedLangException.class)
    public ResponseEntity<String> handleUnSupportedException(UnSupportedLangException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ResourceAccessException and returns a BAD_REQUEST response.
     *
     * @param e the ResourceAccessException exception
     * @return a ResponseEntity containing a generic error message and a BAD_REQUEST status
     */
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceAccessException(ResourceAccessException e) {
        return new ResponseEntity<>("resource is not available", HttpStatus.BAD_REQUEST);
    }
}

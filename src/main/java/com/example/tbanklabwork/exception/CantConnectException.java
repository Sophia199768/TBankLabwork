package com.example.tbanklabwork.exception;

public class CantConnectException extends RuntimeException{
    public CantConnectException(String message) {
        super(message);
    }
}

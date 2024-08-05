package com.example.tbanklabwork.exception;

/**
 * Custom exception thrown when a connection cannot be established.
 */
public class CantConnectException extends RuntimeException {

    /**
     * Constructs a new CantConnectException with the specified detail message.
     *
     * @param message the detail message
     */
    public CantConnectException(String message) {
        super(message);
    }
}

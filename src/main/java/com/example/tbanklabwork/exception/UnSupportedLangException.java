package com.example.tbanklabwork.exception;

/**
 * Custom exception thrown when an unsupported language is requested.
 */
public class UnSupportedLangException extends RuntimeException {

    /**
     * Constructs a new UnSupportedLangException with the specified detail message.
     *
     * @param message the detail message
     */
    public UnSupportedLangException(String message) {
        super(message);
    }
}

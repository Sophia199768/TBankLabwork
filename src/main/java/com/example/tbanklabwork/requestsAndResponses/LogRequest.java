package com.example.tbanklabwork.requestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a log request containing details of a translation operation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogRequest {

    /**
     * The IP address from which the translation request was made.
     */
    private String ipAddress;

    /**
     * The text that was requested for translation.
     */
    private String textToTranslate;

    /**
     * The translated text.
     */
    private String translatedText;
}

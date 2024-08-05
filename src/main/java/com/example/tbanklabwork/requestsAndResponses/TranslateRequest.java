package com.example.tbanklabwork.requestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request for translating text.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslateRequest {

    /**
     * The text that needs to be translated.
     */
    private String textToTranslate;

    /**
     * The code of the source language (e.g., "en" for English).
     */
    private String languageFrom;

    /**
     * The code of the target language (e.g., "es" for Spanish).
     */
    private String languageTo;
}

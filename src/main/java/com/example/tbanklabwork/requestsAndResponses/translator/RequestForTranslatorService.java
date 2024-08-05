package com.example.tbanklabwork.requestsAndResponses.translator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a request for the translator service.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestForTranslatorService {

    /**
     * The code of the source language (e.g., "en" for English).
     */
    private String sourceLanguageCode;

    /**
     * The code of the target language (e.g., "es" for Spanish).
     */
    private String targetLanguageCode;

    /**
     * The list of texts to be translated.
     */
    private List<String> texts;
}

package com.example.tbanklabwork.requestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response containing the result of a translation operation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslateResponse {

    /**
     * The translated text.
     */
    private String text;
}

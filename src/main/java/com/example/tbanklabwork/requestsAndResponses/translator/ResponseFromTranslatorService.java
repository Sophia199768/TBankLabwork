package com.example.tbanklabwork.requestsAndResponses.translator;

import com.example.tbanklabwork.requestsAndResponses.TranslateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a response from the translator service.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFromTranslatorService {

    /**
     * The list of translation responses.
     */
    private List<TranslateResponse> translations;
}

package com.example.tbanklabwork.requestsAndResponses.translator;

import com.example.tbanklabwork.model.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a response from the translator service containing a list of supported languages.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLangFromTranslatorService {

    /**
     * The list of supported languages.
     */
    private List<Language> languages;
}

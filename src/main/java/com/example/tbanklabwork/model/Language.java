package com.example.tbanklabwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a language with its code and name.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    /**
     * The code of the language (e.g., "en" for English).
     */
    private String code;

    /**
     * The name of the language (e.g., "English").
     */
    private String name;
}

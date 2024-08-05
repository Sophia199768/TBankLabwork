package com.example.tbanklabwork.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a translation record.
 */
@Getter
@Setter
public class Translation {

    /**
     * The unique identifier of the translation record.
     */
    private Long id;

    /**
     * The IP address from which the translation request was made.
     */
    private String ipAddress;

    /**
     * The text that needs to be translated.
     */
    private String textToTranslate;

    /**
     * The translated text.
     */
    private String translatedText;

    /**
     * Default constructor.
     */
    public Translation() {}

    /**
     * Constructs a new Translation with the specified IP address, text to translate, and translated text.
     *
     * @param ipAddress the IP address from which the translation request was made
     * @param textToTranslate the text that needs to be translated
     * @param translatedText the translated text
     */
    public Translation(String ipAddress, String textToTranslate, String translatedText) {
        this.ipAddress = ipAddress;
        this.textToTranslate = textToTranslate;
        this.translatedText = translatedText;
    }
}

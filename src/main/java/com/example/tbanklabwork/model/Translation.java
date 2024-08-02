package com.example.tbanklabwork.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Translation {
    private Long id;
    private String ipAddress;
    private String textToTranslate;
    private String translatedText;

    public Translation() {}

    public Translation(String ipAddress, String textToTranslate, String translatedText) {
        this.ipAddress = ipAddress;
        this.textToTranslate = textToTranslate;
        this.translatedText = translatedText;
    }
}

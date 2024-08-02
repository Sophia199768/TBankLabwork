package com.example.tbanklabwork.requestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslateRequest {
    private String textToTranslate;
    private String languageFrom;
    private String languageTo;
}

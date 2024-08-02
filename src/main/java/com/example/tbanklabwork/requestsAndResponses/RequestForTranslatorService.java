package com.example.tbanklabwork.requestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestForTranslatorService {
    private String sourceLanguageCode;
    private String targetLanguageCode;
    private List<String> texts;
}
package com.example.tbanklabwork.requestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogRequest {
    private String ipAddress;
    private String textToTranslate;
    private String translatedText;
}

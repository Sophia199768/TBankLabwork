package com.example.tbanklabwork.requestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFromTranslatorService {
    private List<TranslateResponse> translations;
}

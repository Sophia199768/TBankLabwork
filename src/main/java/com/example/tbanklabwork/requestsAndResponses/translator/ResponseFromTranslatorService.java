package com.example.tbanklabwork.requestsAndResponses.translator;

import com.example.tbanklabwork.requestsAndResponses.TranslateResponse;
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

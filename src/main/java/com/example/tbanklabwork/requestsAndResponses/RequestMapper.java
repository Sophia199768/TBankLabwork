package com.example.tbanklabwork.requestsAndResponses;

import com.example.tbanklabwork.model.Translation;
import com.example.tbanklabwork.requestsAndResponses.translator.RequestForTranslatorService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestMapper {
    public RequestForTranslatorService toTranslateRequest(String word, TranslateRequest request) {
        return new RequestForTranslatorService(request.getLanguageFrom(), request.getLanguageTo(), List.of(word));
    }

    public Translation toTranslation(LogRequest logRequest) {
        return new Translation(logRequest.getIpAddress(), logRequest.getTextToTranslate(), logRequest.getTranslatedText());
    }

    public LogRequest toLogRequest(String ip, TranslateRequest request, TranslateResponse response) {
        return new LogRequest(ip, request.getTextToTranslate(), response.getText());
    }
}

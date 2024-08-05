package com.example.tbanklabwork.requestsAndResponses;

import com.example.tbanklabwork.model.Translation;
import com.example.tbanklabwork.requestsAndResponses.translator.RequestForTranslatorService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Maps between different request and response types used in translation operations.
 */
@Component
public class RequestMapper {

    /**
     * Converts a word and a TranslateRequest to a RequestForTranslatorService.
     *
     * @param word the word to be translated
     * @param request the TranslateRequest containing source and target languages
     * @return a RequestForTranslatorService containing the language codes and the word to translate
     */
    public RequestForTranslatorService toTranslateRequest(String word, TranslateRequest request) {
        return new RequestForTranslatorService(request.getLanguageFrom(), request.getLanguageTo(), List.of(word));
    }

    /**
     * Converts a LogRequest to a Translation entity.
     *
     * @param logRequest the LogRequest containing details of the translation
     * @return a Translation entity with the details from the LogRequest
     */
    public Translation toTranslation(LogRequest logRequest) {
        return new Translation(logRequest.getIpAddress(), logRequest.getTextToTranslate(), logRequest.getTranslatedText());
    }

    /**
     * Converts details from a translation request and response to a LogRequest.
     *
     * @param ip the IP address from which the request originated
     * @param request the TranslateRequest containing the original text and language information
     * @param response the TranslateResponse containing the translated text
     * @return a LogRequest with the IP address, original text, and translated text
     */
    public LogRequest toLogRequest(String ip, TranslateRequest request, TranslateResponse response) {
        return new LogRequest(ip, request.getTextToTranslate(), response.getText());
    }
}

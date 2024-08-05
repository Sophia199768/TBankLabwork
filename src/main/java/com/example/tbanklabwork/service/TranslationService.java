package com.example.tbanklabwork.service;

import com.example.tbanklabwork.model.Language;
import com.example.tbanklabwork.requestsAndResponses.*;
import com.example.tbanklabwork.requestsAndResponses.translator.RequestForTranslatorService;
import com.example.tbanklabwork.requestsAndResponses.translator.ResponseFromTranslatorService;
import com.example.tbanklabwork.requestsAndResponses.translator.ResponseLangFromTranslatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Service for handling translation operations and language support checks.
 */
@Component
@RequiredArgsConstructor
public class TranslationService {

    private static final Integer RETRIES = 3;
    private final Integer MAX_THREADS = 10;
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
    private List<String> supportedLanguages = new ArrayList<>();
    private final RequestMapper mapper;

    /**
     * Creates a translation response for a given TranslateRequest.
     * Translates each word separately and combines the results into a single response.
     *
     * @param translateRequest the request containing text and language information
     * @return a TranslateResponse containing the translated text
     */
    public TranslateResponse createTranslation(TranslateRequest translateRequest) {
        String[] words = translateRequest.getTextToTranslate().split("\\s+");
        String result = Arrays.stream(words).map(word ->
                        processWord(mapper.toTranslateRequest(word, translateRequest)))
                .collect(Collectors.joining(" "));

        return new TranslateResponse(result);
    }

    /**
     * Checks if a given language is supported.
     * Fetches the list of supported languages if not already fetched.
     *
     * @param lang the language code to check
     * @return true if the language is supported, false otherwise
     */
    public Boolean isSupported(String lang) {
        if (supportedLanguages.isEmpty()) supportedLanguages = fetchLanguages();
        return supportedLanguages.contains(lang);
    }

    /**
     * Processes a single word by attempting translation with retries.
     *
     * @param request the request containing the word and language information
     * @return the translated word
     */
    private String processWord(RequestForTranslatorService request) {
        String word;
        List<Callable<String>> subTasks = new ArrayList<>();
        for (int i = 0; i < RETRIES; i++) {
            subTasks.add(() -> translate(request));
        }

        try {
            List<Future<String>> results = executorService.invokeAll(subTasks);
            word = results.stream().map(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).distinct().filter(Objects::nonNull).findFirst().orElse(null);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return word;
    }

    /**
     * Translates a given RequestForTranslatorService using an external translation API.
     *
     * @param request the request containing the text and language information
     * @return the translated text
     */
    public static String translate(RequestForTranslatorService request) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Api-Key " + "AQVNx2hBRzhy6GuM-UAOa5UFX91UrR8rk4w6bFHs");
        HttpEntity<RequestForTranslatorService> body = new HttpEntity<>(request, headers);

        ResponseEntity<ResponseFromTranslatorService> response = restTemplate.exchange(url, HttpMethod.POST, body, ResponseFromTranslatorService.class);

        return response.getBody().getTranslations().stream().findFirst().get().getText();
    }

    /**
     * Fetches the list of supported languages from the translation API.
     *
     * @return a list of supported language codes
     */
    private List<String> fetchLanguages() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://translate.api.cloud.yandex.net/translate/v2/languages";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Api-Key " + "AQVNx2hBRzhy6GuM-UAOa5UFX91UrR8rk4w6bFHs");
        HttpEntity<RequestForTranslatorService> body = new HttpEntity<>(headers);

        ResponseEntity<ResponseLangFromTranslatorService> response = restTemplate.exchange(url, HttpMethod.POST, body, ResponseLangFromTranslatorService.class);

        return response.getBody().getLanguages().stream().map(Language::getCode).toList();
    }
}

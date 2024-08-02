package com.example.tbanklabwork.service;

import com.example.tbanklabwork.requestsAndResponses.*;
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

@Component
@RequiredArgsConstructor
public class TranslationService {
    private static final Integer RETRIES = 3;
    private final Integer MAX_THREADS = 10;
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
    private final RequestMapper mapper;

    public TranslateResponse createTranslation(TranslateRequest translateRequest) {
        String[] words = translateRequest.getTextToTranslate().split("\\s+");
        String result = Arrays.stream(words).map(word ->
                processWord(mapper.toTranslateRequest(word, translateRequest)))
                .collect(Collectors.joining(" "));

        return new TranslateResponse(result);
    }

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

    private static String translate(RequestForTranslatorService request) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Api-Key " + "AQVNx2hBRzhy6GuM-UAOa5UFX91UrR8rk4w6bFHs");
        HttpEntity<RequestForTranslatorService> body = new HttpEntity<>(request, headers);

        ResponseEntity<ResponseFromTranslatorService> response = restTemplate.exchange(url, HttpMethod.POST, body, ResponseFromTranslatorService.class);

        return response.getBody().getTranslations().stream().findFirst().get().getText();
    }
}

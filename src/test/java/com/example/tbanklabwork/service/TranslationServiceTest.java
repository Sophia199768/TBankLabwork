package com.example.tbanklabwork.service;

import com.example.tbanklabwork.requestsAndResponses.*;
import com.example.tbanklabwork.requestsAndResponses.translator.RequestForTranslatorService;
import com.example.tbanklabwork.requestsAndResponses.translator.ResponseFromTranslatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TranslationServiceTest {

    @Mock
    private RequestMapper mapper;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TranslationService translationService;

    private TranslateRequest request;

    @BeforeEach
    void setUp() {
        request = new TranslateRequest();
        request.setTextToTranslate("hello");

        lenient().when(mapper.toTranslateRequest("hello", request)).thenReturn(new RequestForTranslatorService());
    }

    @Test
    void createTranslationUnsuccessful() {
        lenient().when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), eq(ResponseFromTranslatorService.class)))
                .thenThrow(new RuntimeException("Service failure"));

        assertThrows(RuntimeException.class, () -> translationService.createTranslation(request));
    }
}

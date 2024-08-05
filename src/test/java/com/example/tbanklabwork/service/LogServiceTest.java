package com.example.tbanklabwork.service;

import com.example.tbanklabwork.dao.TranslationDao;
import com.example.tbanklabwork.model.Translation;
import com.example.tbanklabwork.requestsAndResponses.LogRequest;
import com.example.tbanklabwork.requestsAndResponses.RequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogServiceTest {

    @Mock
    private TranslationDao translationDao;

    @Mock
    private RequestMapper mapper;

    @InjectMocks
    private LogService logService;

    private LogRequest logRequest;
    private Translation translation;

    @BeforeEach
    void setUp() {
        logRequest = new LogRequest();

        translation = new Translation();

        when(mapper.toTranslation(any(LogRequest.class))).thenReturn(translation);
    }

    @Test
    void logTranslation() {
        logService.logTranslation(logRequest);

        verify(mapper).toTranslation(logRequest);
        verify(translationDao).create(translation);
    }
}

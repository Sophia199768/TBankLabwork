package com.example.tbanklabwork.service;

import com.example.tbanklabwork.dao.TranslationDao;
import com.example.tbanklabwork.requestsAndResponses.LogRequest;
import com.example.tbanklabwork.requestsAndResponses.RequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogService {
    private final TranslationDao translationDao;
    private final RequestMapper mapper;

    public void logTranslation(LogRequest logRequest) {
        translationDao.create(mapper.toTranslation(logRequest));
    }
}

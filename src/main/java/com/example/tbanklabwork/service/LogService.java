package com.example.tbanklabwork.service;

import com.example.tbanklabwork.dao.TranslationDao;
import com.example.tbanklabwork.requestsAndResponses.LogRequest;
import com.example.tbanklabwork.requestsAndResponses.RequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Service for logging translation operations.
 */
@Component
@RequiredArgsConstructor
public class LogService {

    private final TranslationDao translationDao;
    private final RequestMapper mapper;

    /**
     * Logs a translation operation by creating a new translation record in the database.
     *
     * @param logRequest the details of the translation operation to log
     */
    public void logTranslation(LogRequest logRequest) {
        translationDao.create(mapper.toTranslation(logRequest));
    }
}

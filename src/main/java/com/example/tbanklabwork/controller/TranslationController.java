package com.example.tbanklabwork.controller;

import com.example.tbanklabwork.requestsAndResponses.RequestMapper;
import com.example.tbanklabwork.requestsAndResponses.TranslateRequest;
import com.example.tbanklabwork.requestsAndResponses.TranslateResponse;
import com.example.tbanklabwork.service.LogService;
import com.example.tbanklabwork.service.TranslationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/translate")
@RequiredArgsConstructor
public class TranslationController {
    private final TranslationService translationService;
    private final LogService logService;
    private final RequestMapper mapper;

    @PostMapping
    public TranslateResponse translate(@RequestBody TranslateRequest translateRequest, HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        TranslateResponse translated = translationService.createTranslation(translateRequest);
        logService.logTranslation(mapper.toLogRequest(ip, translateRequest, translated));
        return translated;
    }

}

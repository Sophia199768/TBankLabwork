package com.example.tbanklabwork.controller;

import com.example.tbanklabwork.requestsAndResponses.TranslateRequest;
import com.example.tbanklabwork.requestsAndResponses.TranslateResponse;
import com.example.tbanklabwork.service.LogService;
import com.example.tbanklabwork.service.TranslationService;

import com.example.tbanklabwork.requestsAndResponses.RequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TranslationController.class)
@ExtendWith(MockitoExtension.class)
public class TranslationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TranslationService translationService;

    @MockBean
    private LogService logService;

    @MockBean
    private RequestMapper requestMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TranslationController(translationService, logService, requestMapper)).build();
    }

    @Test
    public void testTranslate_Successful() throws Exception {

        TranslateResponse response = new TranslateResponse("Привет");

        when(translationService.isSupported("en")).thenReturn(true);
        when(translationService.isSupported("ru")).thenReturn(true);
        when(translationService.createTranslation(any(TranslateRequest.class))).thenReturn(response);


        mockMvc.perform(post("/api/translate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"textToTranslate\":\"Hello\",\"languageFrom\":\"en\",\"languageTo\":\"ru\"}")
                        .header("X-Forwarded-For", "127.0.0.1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Привет"));

        verify(logService, times(1)).logTranslation(any());
    }


    @Test
    public void testTranslate_NoForwardedHeader() throws Exception {

        TranslateResponse response = new TranslateResponse("Привет");

        when(translationService.isSupported("en")).thenReturn(true);
        when(translationService.isSupported("ru")).thenReturn(true);
        when(translationService.createTranslation(any(TranslateRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/translate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"textToTranslate\":\"Hello\",\"languageFrom\":\"en\",\"languageTo\":\"ru\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Привет"));

        verify(logService, times(1)).logTranslation(any());
    }
}

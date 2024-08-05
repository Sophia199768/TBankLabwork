package com.example.tbanklabwork.requestsAndResponses.translator;

import com.example.tbanklabwork.model.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLangFromTranslatorService {
    private List<Language> languages;
}

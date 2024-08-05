package com.example.tbanklabwork.dao;

import com.example.tbanklabwork.model.Translation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TranslationDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TranslationDao translationDao;

    private Translation translation;

    @BeforeEach
    void setUp() {

        translation = new Translation();
        translation.setIpAddress("127.0.0.1");
        translation.setTextToTranslate("Hello");
        translation.setTranslatedText("Hola");
    }

    @Test
    void create() {
        translationDao.create(translation);

        verify(jdbcTemplate).update(eq("INSERT INTO translation (ip_address, text_to_translate, translated_text) VALUES (?, ?, ?)"),
                eq(translation.getIpAddress()), eq(translation.getTextToTranslate()), eq(translation.getTranslatedText()));
    }

    @Test
    void read() {
        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), any(Long.class)))
                .thenReturn(translation);

        Translation result = translationDao.read(1L);

        verify(jdbcTemplate).queryForObject(eq("SELECT * FROM translation WHERE id = ?"), any(RowMapper.class), eq(1L));
        assert result.equals(translation);
    }

    @Test
    void readAll() {
        List<Translation> translations = Arrays.asList(translation);
        when(jdbcTemplate.query(any(String.class), any(RowMapper.class)))
                .thenReturn(translations);

        List<Translation> result = translationDao.readAll();

        verify(jdbcTemplate).query(eq("SELECT * FROM translation"), any(RowMapper.class));
        assert result.equals(translations);
    }

    @Test
    void update() {
        translationDao.update(translation);

        verify(jdbcTemplate).update(eq("UPDATE translation SET ip_address = ?, text_to_translate = ?, translated_text = ? WHERE id = ?"),
                eq(translation.getIpAddress()), eq(translation.getTextToTranslate()), eq(translation.getTranslatedText()));
    }

    @Test
    void delete() {
        translationDao.delete(1L);

        verify(jdbcTemplate).update(eq("DELETE FROM translation WHERE id = ?"), eq(1L));
    }
}

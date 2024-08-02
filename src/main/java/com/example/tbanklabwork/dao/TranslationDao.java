package com.example.tbanklabwork.dao;

import com.example.tbanklabwork.model.Translation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TranslationDao {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Translation> rowMapper = new RowMapper<Translation>() {
        @Override
        public Translation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Translation request = new Translation();
            request.setIpAddress(rs.getString("ip_address"));
            request.setTextToTranslate(rs.getString("text_to_translate"));
            request.setTranslatedText(rs.getString("translated_text"));
            return request;
        }
    };

    public void create(Translation request) {
        String sql = "INSERT INTO translation (ip_address, text_to_translate, translated_text) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getIpAddress(), request.getTextToTranslate(), request.getTranslatedText());
    }

    public Translation read(Long id) {
        String sql = "SELECT * FROM translation WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Translation> readAll() {
        String sql = "SELECT * FROM translation";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void update(Translation request) {
        String sql = "UPDATE translation SET ip_address = ?, text_to_translate = ?, translated_text = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getIpAddress(), request.getTextToTranslate(), request.getTranslatedText());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM translation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
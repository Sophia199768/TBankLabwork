package com.example.tbanklabwork.dao;

import com.example.tbanklabwork.model.Translation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object for managing Translation entities.
 */
@Repository
@RequiredArgsConstructor
public class TranslationDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Translation> rowMapper = (rs, rowNum) -> {
        Translation translation = new Translation();
        translation.setIpAddress(rs.getString("ip_address"));
        translation.setTextToTranslate(rs.getString("text_to_translate"));
        translation.setTranslatedText(rs.getString("translated_text"));
        return translation;
    };

    /**
     * Inserts a new translation record into the database.
     *
     * @param translation the translation entity to create
     */
    public void create(Translation translation) {
        String sql = "INSERT INTO translation (ip_address, text_to_translate, translated_text) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, translation.getIpAddress(), translation.getTextToTranslate(), translation.getTranslatedText());
    }

    /**
     * Retrieves a translation record by its ID.
     *
     * @param id the ID of the translation record to retrieve
     * @return the translation entity
     */
    public Translation read(Long id) {
        String sql = "SELECT * FROM translation WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    /**
     * Retrieves all translation records from the database.
     *
     * @return a list of all translation entities
     */
    public List<Translation> readAll() {
        String sql = "SELECT * FROM translation";
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * Updates an existing translation record in the database.
     *
     * @param translation the translation entity to update
     */
    public void update(Translation translation) {
        String sql = "UPDATE translation SET ip_address = ?, text_to_translate = ?, translated_text = ? WHERE id = ?";
        jdbcTemplate.update(sql, translation.getIpAddress(), translation.getTextToTranslate(), translation.getTranslatedText());
    }

    /**
     * Deletes a translation record from the database by its ID.
     *
     * @param id the ID of the translation record to delete
     */
    public void delete(Long id) {
        String sql = "DELETE FROM translation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

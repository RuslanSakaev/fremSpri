package com.example.AdminPanelV1.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@Repository
public class DataBaseRepository {
    /**
     * Хранилище данных - База Данных
     */
    private final JdbcTemplate jdbc;
}

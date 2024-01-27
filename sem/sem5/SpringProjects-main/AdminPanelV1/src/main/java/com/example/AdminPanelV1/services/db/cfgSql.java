package com.example.AdminPanelV1.services.db;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Data
@ConfigurationProperties(prefix = "database.sql")
@Configuration
public class cfgSql {

    /**
     * Инкапсулируем из файла конфигурации настройки
     * SQL запрос на создание пользователя
     */
    private String createUser;

    /**
     * SQL запрос на получение списка всех пользователей
     */
    private String allUsers;

    /**
     * SQL запрос на обновление всех пользователей
     */
    private String updateUserByID;

    /**
     * SQL запрос на удаление всех пользователей
     */
    private String delUserByID;
}

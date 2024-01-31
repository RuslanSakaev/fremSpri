package com.example.demo.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "database")
public class DatabaseConfig {
    @Getter
    private String findAllUsers;
    @Getter
    private String saveUser;
    private String deleteById;
    private String update;
    private String getOne;

}

package com.example.RickAndMortyApi.controllers.rest;

import com.example.RickAndMortyApi.models.Characters;
import com.example.RickAndMortyApi.services.ServiceApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST контроллер для работы с запросами
 */
@RestController
public class RestControllerApi {
    /**
     * Сервис по работе с characters
     */
    @Autowired
    private ServiceApi serviceApi;

    /**
     * Запрос получения всех characters
     *
     * @return статус выполнения запроса
     */
    @GetMapping("/api")
    public ResponseEntity<Characters> getAllCharacters() {
        return new ResponseEntity<>(serviceApi.getAllCharacters(), HttpStatus.OK);
    }

}

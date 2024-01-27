package com.example.RickAndMortyApi.services;

import com.example.RickAndMortyApi.models.Characters;
import com.example.RickAndMortyApi.models.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Имплементируемый сервис для работы с удаленным Api
 */
@Service
public class ServiceApiImpl implements ServiceApi {

    /**
     * Обертка для работы с запросами
     */
    @Autowired
    private RestTemplate template;

    /**
     * Управление http заголовкаими
     */
    @Autowired
    private HttpHeaders headers;

    /**
     * Ресурс Api
     */
    private static final String CHARACTER_API = "https://rickandmortyapi.com/api/character";

    /**
     * Получение всех characters по внешнему Api
     *
     * @return Characters
     */
    @Override
    public Characters getAllCharacters() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> response = template.exchange(CHARACTER_API, HttpMethod.GET, entity, Characters.class);
        return response.getBody();
    }

    /**
     * Получение Characters - results по статусу
     *
     * @param status необходимый статус
     * @return список нужных результатов
     */
    @Override
    public List<Results> getByStatus(String status) {
        Characters characters = getAllCharacters();
        return characters.getResults().stream().filter(item -> item.getStatus().equals(status)).collect(Collectors.toList());
    }

    /**
     * Получение списка результатов по имени
     *
     * @param name имя
     * @return список результатов
     */
    @Override
    public List<Results> getByName(String name) {
        return getAllCharacters().getResults().stream().filter(item -> item.getName().equals(name)).collect(Collectors.toList());
    }
}

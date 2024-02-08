package ru.sakaev.rickAndMorty.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import ru.sakaev.rickAndMorty.domain.Character;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

@Service
public class ServiceApiImpl implements ServiceApi {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    @Value("${api.character.url}")
    private String characterApiUrl;

    public ServiceApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.headers = new HttpHeaders();
    }

    @Override
    public List<Character> getAllCharacters() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Character[]> response = restTemplate.exchange(characterApiUrl, HttpMethod.GET, entity, Character[].class);
        return List.of(Objects.requireNonNull(response.getBody()));
    }
}

package ru.sakaev.rickAndMorty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;
import ru.sakaev.rickAndMorty.domain.Character;
import ru.sakaev.rickAndMorty.domain.Result;

import java.util.List;

@Controller
public class CharacterController {

    @Value("${api.character.url}")
    private String characterApiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public CharacterController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String getCharacters(Model model) {
        ResponseEntity<Result<Character>> responseEntity = restTemplate.exchange(
                characterApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result<Character>>() {}
        );
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Result<Character> result = responseEntity.getBody();
            if (result != null) {
                List<Character> characters = result.getResults();
                model.addAttribute("characters", characters);
            }
        } else {
            model.addAttribute("errorMessage", "Failed to fetch characters. Please try again later.");
        }
        return "characters";
    }

    @GetMapping("/character/{id}")
    public String getCharacterDetail(Model model, @PathVariable("id") int id) {
        String characterDetailUrl = UriComponentsBuilder.fromUriString(characterApiUrl)
                .pathSegment(String.valueOf(id))
                .build()
                .toUriString();
        ResponseEntity<Character> responseEntity = restTemplate.getForEntity(characterDetailUrl, Character.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Character character = responseEntity.getBody();
            if (character != null) {
                model.addAttribute("character", character);
            }
        } else {
            model.addAttribute("errorMessage", "Failed to fetch character details. Please try again later.");
        }
        return "character";
    }
}

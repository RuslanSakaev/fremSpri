package ru.sakaev.rickAndMorty.service;

import ru.sakaev.rickAndMorty.domain.Character;

import java.util.List;

public interface ServiceApi {
    List<Character> getAllCharacters();
}

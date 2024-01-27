package com.example.RickAndMortyApi.services;

import com.example.RickAndMortyApi.models.Characters;
import com.example.RickAndMortyApi.models.Results;
import java.util.List;

public interface ServiceApi {
    public Characters getAllCharacters();

    public List<Results> getByStatus(String status);

    public List<Results> getByName(String name);
}

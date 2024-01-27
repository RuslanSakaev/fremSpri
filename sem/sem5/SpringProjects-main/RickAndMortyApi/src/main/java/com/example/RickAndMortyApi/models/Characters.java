package com.example.RickAndMortyApi.models;

import lombok.Data;

import java.util.List;

@Data
public class Characters {
    private Info info;
    private List<Results> results;
}

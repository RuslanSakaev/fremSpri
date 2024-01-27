package com.example.RickAndMortyApi.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Results {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;
    private LocalDateTime created;
}

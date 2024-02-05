package ru.sakaevrs.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

@Setter
@Getter
@Entity
public class UsersProject extends ru.sakaevrs.model.EntityWithRelation {

    private Long projectId;
    private Long userId;

}

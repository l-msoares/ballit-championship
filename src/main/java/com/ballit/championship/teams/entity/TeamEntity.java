package com.ballit.championship.teams.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TeamEntity {
    @Id
    public Integer id;
    public String name;
    public String warCry;
    public Integer foundationYear;

    public TeamEntity(String name, String warCry, Integer foundationYear) {
        this.name = name;
        this.warCry = warCry;
        this.foundationYear = foundationYear;
    }

    public TeamEntity() {

    }
}

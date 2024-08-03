package com.ballit.championship.tournament.entity;

import com.ballit.championship.teams.entity.TeamEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Match {
    @Id
    public Integer id;
    Integer phase;
    @OneToOne
    TeamEntity team1;
    @OneToOne
    TeamEntity team2;
    Integer points1;
    Integer points2;

    public Match(Integer phase, TeamEntity team1, TeamEntity team2, Integer points1, Integer points2) {
        this.phase = phase;
        this.team1 = team1;
        this.team2 = team2;
        this.points1 = points1;
        this.points2 = points2;
    }

    public Match() {

    }

    public Integer getPhase() {
        return phase;
    }

    public TeamEntity getTeam1() {
        return team1;
    }

    public TeamEntity getTeam2() {
        return team2;
    }

    public Integer getPoints1() {
        return points1;
    }

    public Integer getPoints2() {
        return points2;
    }
}

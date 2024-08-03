package com.ballit.championship.tournament.entity;

import com.ballit.championship.teams.entity.TeamEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TournamentEntity {
    @Id
    public Integer id;
    @OneToMany
    private List<TeamEntity> teams = new ArrayList<>();

    @OneToMany
    private List<Match> matches = new ArrayList<>();

    private boolean started = false;

    public boolean isStarted() {
        return started;
    }

    public TournamentEntity() {
    }

    public List<TeamEntity> getTeams(){
        return teams;
    }

    public void addTeams(List<TeamEntity> team){
        teams.addAll(team);
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public List<Match> getMatches(){
        return matches;
    }

}


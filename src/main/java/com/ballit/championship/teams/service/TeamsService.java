package com.ballit.championship.teams.service;

import com.ballit.championship.teams.entity.TeamEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamsService {
    List<TeamEntity> teams = new ArrayList<>();

    public void addTeam(TeamEntity team) {
        this.teams.add(team);
    }

    public List<TeamEntity> getTeams() {
        return this.teams;
    }
}

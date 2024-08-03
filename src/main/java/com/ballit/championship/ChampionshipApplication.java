package com.ballit.championship;

import com.ballit.championship.teams.entity.TeamEntity;
import com.ballit.championship.teams.service.TeamsService;
import com.ballit.championship.tournament.entity.TournamentEntity;
import com.ballit.championship.tournament.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SpringBootApplication
public class ChampionshipApplication {

    @Autowired
    TeamsService teamsService;

    @Autowired
    TournamentService tournamentService;

    public static void main(String[] args) {
        SpringApplication.run(ChampionshipApplication.class, args);
    }

    @GetMapping
    public String championship() {
        return "ok";
    }

    @GetMapping("teams")
    public List<TeamEntity> getTeams() {
        return this.teamsService.getTeams();
    }

    @PutMapping("tournament/teams/add")
    public void addTeams(@RequestBody List<TeamEntity> teams) {
        this.tournamentService.addTeams(teams);
    }

    @PostMapping("tournament/start")
    public String startTournament() {
        return this.tournamentService.startTournament();
    }

    @GetMapping("tournament")
    public TournamentEntity getTournament() {
        return this.tournamentService.getTournament();
    }
}

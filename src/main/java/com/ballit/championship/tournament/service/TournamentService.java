package com.ballit.championship.tournament.service;

import com.ballit.championship.teams.entity.TeamEntity;
import com.ballit.championship.tournament.entity.Match;
import com.ballit.championship.tournament.entity.TournamentEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    TournamentEntity tournament = new TournamentEntity();

    public TournamentService() {
    }

    public void addTeams(List<TeamEntity> teams) {
        boolean isLowerThanLimit = tournament.getTeams().size() + teams.size() < 16;
        if (isLowerThanLimit) {
            TeamEntity existentTeam = null;
            for (TeamEntity team: teams) {
                if (tournament.getTeams().stream().anyMatch(val -> val.name.equals(team.name))) {
                    existentTeam = team;
                }
            }

            if (existentTeam == null) {
                this.tournament.addTeams(teams);
                return;
            }
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Team already exists" + existentTeam.name);

        }
    }

    public TournamentEntity getTournament() {
        return this.tournament;
    }

    public String startTournament() {
        if (!tournament.isStarted()) {
            if (tournament.getTeams().size() % 2 == 0) {
                if (tournament.getTeams().size() >= 8 && tournament.getTeams().size() <= 16) {
                    tournament.setStarted(true);
                    Integer numberOfLoops = 0;
                    Random generator = new Random();
                    List<TeamEntity> alreadyMatchedTeams = new ArrayList<>();

                    while (numberOfLoops < tournament.getTeams().size()) {
                        List<TeamEntity> notMatchedTeams = tournament.getTeams()
                                .stream()
                                .filter(team -> alreadyMatchedTeams.stream().noneMatch(already -> already.name.equals(team.name)))
                                .toList();
                        while (true) {
                            if (notMatchedTeams.size() >= 2) {
                                TeamEntity team1 = notMatchedTeams
                                        .get(generator.nextInt(notMatchedTeams.size()));

                                TeamEntity team2 = notMatchedTeams
                                        .get(generator.nextInt(tournament.getTeams().size() - alreadyMatchedTeams.size()));

                                if (!team1.name.equals(team2.name) && alreadyMatchedTeams.stream().noneMatch(
                                        team ->
                                                team1.name.equals(team.name) ||
                                                        team2.name.equals(team.name)
                                )) {
                                    alreadyMatchedTeams.add(team1);
                                    alreadyMatchedTeams.add(team2);
                                    tournament.addMatch(new Match(1, team1, team2, 0, 0));
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        numberOfLoops++;
                    }

                    return "Tournament started!";
                }
                throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Number of teams not reached");
            }
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "The number of teams must be even");
        }

        throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Tournament already started!");
    }
}

package com.example.liderit.services;

import com.example.liderit.models.Team;
import com.example.liderit.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public List<Team> findAllFilteredBySportKind(String sportKind) {
        return teamRepository.findAllBySportKind(sportKind);
    }

    public List<Team> findAllFilteredByCreationDate(LocalDate startDate, LocalDate endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        return teamRepository.findAllByCreationDateBetween(startDate, endDate);
    }


    public Team postTeam(Team team) {
        return teamRepository.saveAndFlush(team);
    }

    public ResponseEntity<Team> putTeamById(Integer teamId, Team team) {
        if (!teamRepository.existsById(teamId)) {
            return new ResponseEntity<>(teamRepository.saveAndFlush(team), HttpStatus.CREATED);
        }
        team.setId(teamId);
        return new ResponseEntity<>(teamRepository.saveAndFlush(team),HttpStatus.OK);
    }

    public void deleteTeamById(Integer teamId) {
        teamRepository.deleteById(teamId);
    }
}

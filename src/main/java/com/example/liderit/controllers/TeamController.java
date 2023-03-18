package com.example.liderit.controllers;

import com.example.liderit.models.Team;
import com.example.liderit.services.TeamService;
import com.example.liderit.utils.CodeHelper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/sport/teams")
public class TeamController {
    private final TeamService teamService;

    TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<Collection<Team>> getAllTeams() {
        return CodeHelper.checkForEmpty(teamService.findAll());
    }

    @GetMapping("/filters/sport-kinds")
    public ResponseEntity<Collection<Team>> getFilteredTeamsBySportKind(@RequestParam(name = "sportKind") String sportKind) {
        return CodeHelper.checkForEmpty(teamService.findAllFilteredBySportKind(sportKind));
    }

    @GetMapping("/filters/dates")
    public ResponseEntity<Collection<Team>> getFilteredTeamsByCreationDate(
            @RequestParam(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        return CodeHelper.checkForEmpty(teamService.findAllFilteredByCreationDate(startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<Team> postTeam(@RequestBody Team team) {
        return new ResponseEntity<>(teamService.postTeam(team), HttpStatus.CREATED);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Team> putTeam(@PathVariable Integer teamId, @RequestBody Team team) {
        return teamService.putTeamById(teamId, team);
    }


    @DeleteMapping("/{teamId}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable Integer teamId) {
        teamService.deleteTeamById(teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

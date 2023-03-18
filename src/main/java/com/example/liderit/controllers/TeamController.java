package com.example.liderit.controllers;

import com.example.liderit.models.Team;
import com.example.liderit.services.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sport/teams")
public class TeamController {
    private final TeamService teamService;

    TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    List<Team> getTeams() {
        return teamService.findAll();
    }

    @PostMapping
    Team postTeam(@RequestBody Team newTeam) {
        return teamService.postTeam(newTeam);
    }

    @PutMapping("/{id}")
    ResponseEntity<Team> putTeam(@PathVariable Integer id, @RequestBody Team team) {
        return teamService.putTeam(team,id);
    }

    @DeleteMapping("/{id}")
    void deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeam(id);
    }


}

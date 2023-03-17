package com.example.liderit.controllers;

import com.example.liderit.models.Team;
import com.example.liderit.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return teamService.addTeam(newTeam);
    }

    @PutMapping("/{id}")
    ResponseEntity<Team> putTeam(@PathVariable Integer id, @RequestBody Team team) {
        //FIXME: не работает изменение данных. Возвращается 201, но сами данные не меняются

        // Если объект существует, меняем его и возвращаем 200, если нет, то 201
        return teamService.getTeam(id).map(value -> new ResponseEntity<>(teamService.addTeam(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(postTeam(team), HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    void deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeam(id);
    }


}

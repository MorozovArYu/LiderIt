package com.example.liderit.controllers;

import com.example.liderit.models.Team;
import com.example.liderit.models.abstr_model.Model;
import com.example.liderit.models.Player;
import com.example.liderit.services.PlayerService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/sport/players")
public class PlayerController {
    private final PlayerService playerService;

    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }



    @GetMapping("/{teamId}")
    public ResponseEntity<Collection<Player>> getPlayersFromTeamByTeamId(@PathVariable Integer teamId) {
        return playerService.getPlayersFromTeamByTeamId(teamId);
    }

    @GetMapping("/{teamId}/filters/roles")
    public ResponseEntity<Collection<Player>> getPlayersFromTeamFilteredByRole(@PathVariable Integer teamId,
                                                                               @RequestParam(name = "role") String role) {
        return playerService.getPlayersFromTeamByTeamIdFilteredByRole(teamId, role);
    }


    @PostMapping("/{teamId}")
    public ResponseEntity<Player> postPlayer(@RequestBody Player player,
                                             @PathVariable Integer teamId) {
        return playerService.postPlayerByTeamId(teamId, player);
    }


    @PutMapping("/{playerId}")
    public ResponseEntity<Player> changePlayerByPlayerId(@PathVariable Integer playerId,
                                                         @RequestBody Player player) {
        return playerService.putPlayerByPlayerId(playerId, player);
    }

    @PatchMapping("/{playerId}")
    public ResponseEntity<Team> changePlayerByPlayerTeam(@PathVariable Integer playerId,
                                                                      @RequestParam(name = "newTeamId") Integer newTeamId) {
        return playerService.changePlayerTeamByPlayerId(playerId, newTeamId);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable Integer playerId) {
        playerService.deletePlayerById(playerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

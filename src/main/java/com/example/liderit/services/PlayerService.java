package com.example.liderit.services;


import com.example.liderit.exceptions_handler.exceptions.not_found.PlayerNotFoundException;
import com.example.liderit.exceptions_handler.exceptions.not_found.TeamNotFoundException;
import com.example.liderit.models.abstr_model.Model;
import com.example.liderit.models.Player;
import com.example.liderit.models.Team;
import com.example.liderit.repository.PlayerRepository;
import com.example.liderit.repository.TeamRepository;
import com.example.liderit.utils.CodeHelper;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public ResponseEntity<Player> postPlayerByTeamId(Integer teamId, Player player) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
        player.setTeam(team);
        return new ResponseEntity<>(playerRepository.saveAndFlush(player), HttpStatus.CREATED);
    }

    public ResponseEntity<Team> changePlayerTeamByPlayerId(Integer playerId, Integer newTeamId) {
        Team team = teamRepository.findById(newTeamId).orElseThrow(() -> new TeamNotFoundException(newTeamId));
        Player player = playerRepository.findById(playerId).orElseThrow(() ->new PlayerNotFoundException(playerId));
        player.setTeam(team);
        return new ResponseEntity<>(playerRepository.saveAndFlush(player).getTeam(),HttpStatus.OK);
    }

    public ResponseEntity<Player> putPlayerByPlayerId(Integer playerId, Player player) {
        Player currentPlayer = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));
        player.setId(playerId);
        player.setTeam(currentPlayer.getTeam());
        return new ResponseEntity<>(playerRepository.saveAndFlush(player), HttpStatus.OK);
    }

    public void deletePlayerById(Integer playerId) {
        if (!playerRepository.existsById(playerId)) throw new PlayerNotFoundException(playerId);
        playerRepository.deleteById(playerId);
    }

    public ResponseEntity<Collection<Player>> getPlayersFromTeamByTeamId(Integer teamId) {
        return CodeHelper.checkForEmpty(teamRepository.findById(teamId).orElseThrow(()-> new TeamNotFoundException(teamId)).getPlayerList());
    }

    public ResponseEntity<Collection<Player>> getPlayersFromTeamByTeamIdFilteredByRole(Integer teamId, String role) {
        if (!teamRepository.existsById(teamId)) throw new TeamNotFoundException(teamId);
        return CodeHelper.checkForEmpty(playerRepository.getPlayersFromTeamByTeamIdFilteredByRole(teamId, role));
    }

}

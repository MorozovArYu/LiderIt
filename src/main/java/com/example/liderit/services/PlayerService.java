package com.example.liderit.services;


import com.example.liderit.exceptions_handler.exceptions.PlayerNotFoundException;
import com.example.liderit.exceptions_handler.exceptions.TeamNotFoundException;
import com.example.liderit.models.Player;
import com.example.liderit.models.Team;
import com.example.liderit.repository.PlayerRepository;
import com.example.liderit.repository.TeamRepository;
import com.example.liderit.utils.CodeHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public ResponseEntity<Player> postPlayerById(Integer teamId, Player player) {
        Optional<Team> teamOpt = teamRepository.findById(teamId);
        Team team = teamOpt.orElseThrow(() -> new TeamNotFoundException(teamId));
        team.addPlayerToList(player);
        teamRepository.saveAndFlush(team);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    public ResponseEntity<Player> changePlayerTeam(Integer playerId, Integer newTeamId) {
        Team team = teamRepository.findById(newTeamId).orElseThrow(() -> new TeamNotFoundException(newTeamId));
        Player player = playerRepository.findById(playerId).orElseThrow(() ->new PlayerNotFoundException(playerId));
        player.setTeam(team);
        return new ResponseEntity<>(playerRepository.saveAndFlush(player), HttpStatus.OK);
    }

    public ResponseEntity<Player> putPlayerById(Integer playerId, Player player) {
        if (!playerRepository.existsById(playerId)) {
            return new ResponseEntity<>(playerRepository.saveAndFlush(player), HttpStatus.CREATED);
        }
        player.setId(playerId);
        return new ResponseEntity<>(playerRepository.saveAndFlush(player), HttpStatus.OK);
    }

    public void deletePlayerById(Integer playerId) {
        playerRepository.deleteById(playerId);
    }

    public ResponseEntity<Collection<Player>> getPlayersFromTeamByTeamId(Integer teamId) {
        return CodeHelper.checkForEmpty(teamRepository.findById(teamId).orElseThrow(()-> new TeamNotFoundException(teamId)).getPlayerList());
    }

    public ResponseEntity<Collection<Player>> getPlayersFromTeamByTeamIdFilteredByRole(Integer teamId, String role) {
        return CodeHelper.checkForEmpty(playerRepository.getPlayersFromTeamByTeamIdFilteredByRole(teamId, role));
    }

}

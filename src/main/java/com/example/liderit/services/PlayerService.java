package com.example.liderit.services;


import com.example.liderit.models.Player;
import com.example.liderit.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player postPlayer(Player player) {
        return playerRepository.saveAndFlush(player);
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }
    public ResponseEntity<Player> putPlayer(Player player, Integer id) {
        if (playerRepository.existsById(id)) {
            player.setId(id);
            return new ResponseEntity<>(playerRepository.saveAndFlush(player), HttpStatus.OK);
        }
        return new ResponseEntity<>(playerRepository.saveAndFlush(player), HttpStatus.CREATED);
    }

    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }
}

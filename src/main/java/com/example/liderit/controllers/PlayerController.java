package com.example.liderit.controllers;

import com.example.liderit.models.Player;
import com.example.liderit.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sport/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getAll(){
        //FIXME Удалить метод, используется для тестов
        return playerService.findAll();
    }
    @PostMapping()
    public Player postPlayer(@RequestBody Player player) {
        return playerService.postPlayer(player);
    }

    @PutMapping("/{id}")
    ResponseEntity<Player> putPlayer(@PathVariable Integer id, @RequestBody Player player) {
        return playerService.putPlayer(player, id);
    }


    @DeleteMapping("/{id}")
    void deletePlayer(@PathVariable Integer id) {
        playerService.deletePlayer(id);
    }
}

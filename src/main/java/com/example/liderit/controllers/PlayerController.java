package com.example.liderit.controllers;

import com.example.liderit.models.Player;
import com.example.liderit.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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


    /*FIXME При отправке post запроса создается игрок,
     *       но team_id не добавляется, возвращается ответ:
     *       201 (Created) значение игрока с id 0*/
    @PostMapping("/{teamId}")
    public ResponseEntity<Player> postPlayer(@RequestBody Player player,
                                             @PathVariable Integer teamId) {
        return playerService.postPlayerById(teamId, player);
    }

    /*FIXME при отправке запроса с несуществующим player id создает
     *      нового игрока, а должен выбрасывать PlayerNotFound
     *    */
    @PutMapping("/{playerId}")
    public ResponseEntity<Player> changePlayerTeam(@PathVariable Integer playerId,
                                                   @RequestParam(name = "newTeamId",
                                                           required = false) Integer newTeamId,
                                                   @RequestBody Player player) {
        if (Objects.isNull(newTeamId)) return playerService.putPlayerById(playerId, player);
        return playerService.changePlayerTeam(playerId, newTeamId);
    }

    /*FIXME Если указать несуществующий id, то возвращается
     *       500 (INTERNAL ERROR), а должна PlayerNotFound (или
     *       PlayerNotExist)*/
    @DeleteMapping("/{playerId}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable Integer playerId) {
        playerService.deletePlayerById(playerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

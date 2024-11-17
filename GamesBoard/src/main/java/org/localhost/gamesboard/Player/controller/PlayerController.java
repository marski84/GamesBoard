package org.localhost.gamesboard.Player.controller;

import org.localhost.gamesboard.Player.dto.PlayerDataDto;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.Player.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping("/{playerName}")
    public ResponseEntity<PlayerDataDto> addPlayer(@PathVariable String playerName) {
        Player player = playerService.registerPlayer(playerName);

        PlayerDataDto playerDataDto = PlayerDataDto.fromPlayer(player);
        return ResponseEntity.status(HttpStatus.OK).body(playerDataDto);
    }


    @DeleteMapping("/{playerId}")
    public ResponseEntity<PlayerDataDto> removePlayer(@PathVariable int playerId) {
        Player removedPlayer = playerService.removePlayer(playerId);
        PlayerDataDto playerDataDto = PlayerDataDto.fromPlayer(removedPlayer);
        return ResponseEntity.status(HttpStatus.OK).body(playerDataDto);
    }

    @GetMapping("/{playerName}")
    public ResponseEntity<PlayerDataDto> getPlayer(@PathVariable String playerName) {
        Player player = playerService.getPlayerData(playerName);

        PlayerDataDto playerData = PlayerDataDto.fromPlayer(player);

        return ResponseEntity.status(HttpStatus.OK).body(playerData);
    }

    @GetMapping("/id/{playerId}")
    public ResponseEntity<PlayerDataDto> getPlayer(@PathVariable int playerId) {
        Player player = playerService.getPlayerData(playerId);

        PlayerDataDto playerData = PlayerDataDto.fromPlayer(player);


        return ResponseEntity.status(HttpStatus.OK).body(playerData);
    }
}
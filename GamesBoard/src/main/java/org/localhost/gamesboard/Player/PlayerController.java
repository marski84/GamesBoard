package org.localhost.gamesboard.Player;

import org.localhost.gamesboard.Player.dto.PlayerDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerHandler playerHandler;

    public PlayerController(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    @PostMapping("/{playerName}")
    public ResponseEntity<PlayerDataDto> addPlayer(@PathVariable String playerName) {
        PlayerDataDto player = playerHandler.addPlayer(playerName);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }


    @DeleteMapping("/{playerId}")
    public ResponseEntity<PlayerDataDto> removePlayer(@PathVariable int playerId) {
        PlayerDataDto player = playerHandler.removePlayer(playerId);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @GetMapping("/{playerName}")
    public ResponseEntity<PlayerDataDto> getPlayer(@PathVariable String playerName) {
        PlayerDataDto player = playerHandler.getPlayer(playerName);

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @GetMapping("/id/{playerId}")
    public ResponseEntity<PlayerDataDto> getPlayer(@PathVariable int playerId) {
        PlayerDataDto player = playerHandler.getPlayer(playerId);

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }
}

package org.localhost.gamesboard.Player;

import org.localhost.gamesboard.Dto.PlayerDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/player")
public class PlayerController {
    private final PlayerFacade playerFacade;

    public PlayerController(PlayerFacade playerFacade) {
        this.playerFacade = playerFacade;
    }

    @PostMapping("{playerName}")
    public ResponseEntity<PlayerDataDto> addPlayer(@PathVariable String playerName) {
        PlayerDataDto player = playerFacade.addPlayer(playerName);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }


    @DeleteMapping("{playerId}")
    public ResponseEntity<PlayerDataDto> removePlayer(@PathVariable int playerId) {
        PlayerDataDto player = playerFacade.removePlayer(playerId);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @GetMapping("{playerName}")
    public ResponseEntity<PlayerDataDto> getPlayer(@PathVariable String playerName) {
        PlayerDataDto player = playerFacade.getPlayer(playerName);

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @GetMapping("id/{playerId}")
    public ResponseEntity<PlayerDataDto> getPlayer(@PathVariable int playerId) {
        PlayerDataDto player = playerFacade.getPlayer(playerId);

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }
}

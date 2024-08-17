package org.localhost.gamesboard.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.localhost.gamesboard.facade.GameFacade;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/games")
public class GameController {
    private final GameFacade gameFacade;

    public GameController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @PostMapping()
    public ResponseEntity<Game> createGame(@RequestBody String gameName) {
        Game newGame = gameFacade.createGame(gameName);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @GetMapping("manage-game/start/{gameId}")
    public ResponseEntity<Game> startGame(@PathVariable int gameId) {
        Game startedGame = gameFacade.startGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(startedGame);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable int gameId) {
        Game game = gameFacade.getGameById(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @GetMapping("getGameByName/{gameName}")
    public ResponseEntity<Game> getGame(@PathVariable String gameName) {
        Game game = gameFacade.getGameByName(gameName);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @PostMapping("/player")
    public ResponseEntity<Player> addPlayer(@RequestBody JsonNode playerName) {
        String name = playerName.get("playerName").asText();
        Player player = gameFacade.addPlayer(name);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @DeleteMapping("/player/{playerId}")
    public ResponseEntity<Player> removePlayer(@PathVariable int playerId) {
        Player player = gameFacade.removePlayer(playerId);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }


}

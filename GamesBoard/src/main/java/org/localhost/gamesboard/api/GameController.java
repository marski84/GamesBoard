package org.localhost.gamesboard.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.localhost.gamesboard.facade.GameFacade;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/games")
public class GameController {
    private final GameFacade gameFacade;
    private final ObjectMapper objectMapper;

    public GameController(GameFacade gameFacade, ObjectMapper objectMapper) {
        this.gameFacade = gameFacade;
        this.objectMapper = objectMapper;
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

    @GetMapping("manage-game/end/{gameId}")
    public ResponseEntity<Game> endGame(@PathVariable int gameId) {
        Game finishedGame = gameFacade.endGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(finishedGame);
    }

    @GetMapping("manage-game/add-player/{gameId}/{playerId}")
    public ResponseEntity<Game> addPlayerToGame(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
        Game game = gameFacade.addPlayerToGame(gameId, playerId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @GetMapping("manage-game/remove-player/{gameId}/{playerId}")
    public ResponseEntity<Game> removePlayerFromGame(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
        Game game = gameFacade.removePlayerFromGame(gameId, playerId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }


    @PostMapping("manage-game/updateGameScore/{gameId}")
    public ResponseEntity<Game> updateGameScore(@PathVariable int gameId, @RequestBody ArrayNode gameScore) {
        if ( gameScore == null || gameScore.isEmpty()) {
            throw new IllegalArgumentException("Game score cannot be null or empty");
        }
        try {
            List<PlayerScore> playerScores = objectMapper.convertValue(
                    gameScore,
                    new TypeReference<List<PlayerScore>>() {}
                    );

            Game result = gameFacade.saveGameScore(gameId, playerScores);


            return ResponseEntity.ok().body(result); //
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error processing game score", e);
        }

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
        if (playerName.get("playerName").isEmpty() || playerName.get("playerName").isNull()) {
            throw new IllegalArgumentException("Player name and score cannot be empty or null");
        }
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

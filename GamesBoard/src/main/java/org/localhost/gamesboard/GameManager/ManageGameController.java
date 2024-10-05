package org.localhost.gamesboard.GameManager;


import org.localhost.gamesboard.Aggregate.AggregateHandler;
import org.localhost.gamesboard.Game.dto.GameDtoBuilder;
import org.localhost.gamesboard.GameManager.model.PlayerScore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage-game/")
public class ManageGameController {
    private final GameManagerHandler gameManagerHandler;
    private final AggregateHandler aggregateHandler;

    public ManageGameController(GameManagerHandler gameManagerHandler, AggregateHandler aggregateHandler) {
        this.gameManagerHandler = gameManagerHandler;
        this.aggregateHandler = aggregateHandler;
    }

    @GetMapping("/start/{gameId}")
    public ResponseEntity<GameDtoBuilder> startGame(@PathVariable int gameId) {
        GameDtoBuilder startedGame = gameManagerHandler.startGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(startedGame);
    }

    @GetMapping("/end/{gameId}")
    public ResponseEntity<GameDtoBuilder> endGame(@PathVariable int gameId) {
        GameDtoBuilder finishedGame = gameManagerHandler.endGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(finishedGame);
    }

    @PostMapping("/update-game-score/{gameId}")
    public ResponseEntity<GameDtoBuilder> updateGameScore(@PathVariable int gameId,
                                                          @RequestBody List<PlayerScore> gameScore) {
        GameDtoBuilder result = gameManagerHandler.saveGameScore(gameId, gameScore);
        return ResponseEntity.ok().body(result);
    }


    @PatchMapping("/register-player/{gameId}/{playerId}")
    public ResponseEntity<GameDtoBuilder> registerPlayer(@PathVariable int gameId, @PathVariable int playerId) {
        GameDtoBuilder gameDto = aggregateHandler.addPlayerToGame(gameId, playerId);
        return ResponseEntity.ok(gameDto);
    }

    @PatchMapping("/unregister-player/{gameId}/{playerId}")
    public ResponseEntity<GameDtoBuilder> unregisterPlayer(@PathVariable int gameId, @PathVariable int playerId) {
        GameDtoBuilder gameDto = aggregateHandler.removePlayerFromGame(gameId, playerId);
        return ResponseEntity.ok(gameDto);
    }

}

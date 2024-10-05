package org.localhost.gamesboard.Game;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.Game.dto.GameDtoBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/games")
public class GameController {
    private final GameHandler gameHandler;

    public GameController(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDtoBuilder> getGame(@PathVariable int gameId) {
        GameDtoBuilder game = gameHandler.getGameById(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @GetMapping("/get-game-by-name/{gameName}")
    public ResponseEntity<GameDtoBuilder> getGame(@PathVariable String gameName) {
        GameDtoBuilder game = gameHandler.getGameByName(gameName);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GameDtoBuilder>> getAllGames() {
        List<GameDtoBuilder> games = gameHandler.getAllGames();
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<GameDtoBuilder> deleteGame(@PathVariable int gameId) {
        GameDtoBuilder game = gameHandler.getGameById(gameId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(game);
    }

    @PostMapping("/create/{gameName}")
    public ResponseEntity<GameDtoBuilder> createGame(
            @PathVariable String gameName
    ) {
        GameDtoBuilder createdGame = gameHandler.createGame(gameName);
        return ResponseEntity.status(HttpStatus.OK).body(createdGame);

    }
}

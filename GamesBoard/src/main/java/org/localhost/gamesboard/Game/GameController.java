package org.localhost.gamesboard.Game;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.Dto.GameDataDto;
import org.localhost.gamesboard.Dto.GameDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/games")
public class GameController {
    private final GameFacade gameFacade;

    public GameController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDataDto> getGame(@PathVariable int gameId) {
        GameDataDto game = gameFacade.getGameById(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @GetMapping("/get-game-by-name/{gameName}")
    public ResponseEntity<GameDataDto> getGame(@PathVariable String gameName) {
        GameDataDto game = gameFacade.getGameByName(gameName);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GameDataDto>> getAllGames() {
        List<GameDataDto> games = gameFacade.getAllGames();
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<GameDataDto> deleteGame(@PathVariable int gameId) {
        GameDataDto game = gameFacade.getGameById(gameId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(game);
    }

    @PostMapping("/create/{gameName}")
    public ResponseEntity<GameDto> createGame(
            @PathVariable String gameName
    ) {
        GameDto createdGame = gameFacade.createGame(gameName);
        return ResponseEntity.status(HttpStatus.OK).body(createdGame);

    }


}

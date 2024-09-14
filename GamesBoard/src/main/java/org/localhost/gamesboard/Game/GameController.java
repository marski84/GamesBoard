package org.localhost.gamesboard.Game;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.Dto.GameDataDto;
import org.localhost.gamesboard.Dto.GameDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("api/games")
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

    //get-game-by-name
    @GetMapping("get-game-by-name/{gameName}")
    public ResponseEntity<GameDataDto> getGame(@PathVariable String gameName) {
        GameDataDto game = gameFacade.getGameByName(gameName);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }
}
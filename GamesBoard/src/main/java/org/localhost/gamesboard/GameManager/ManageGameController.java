package org.localhost.gamesboard.GameManager;
import org.localhost.gamesboard.Dto.*;
import org.localhost.gamesboard.Game.GameFacade;
import org.localhost.gamesboard.model.PlayerScore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * root:
 *  - domena-x -> DomenaXApiInterface
 *  - domena-y -> DomenaYApiInterface
 *  - infra
 *      - DomenaXYFacade
 */
@RestController
@RequestMapping("/api/manage-game/")
public class ManageGameController {
    private final GameManagerFacade gameManagerFacade;

    public ManageGameController(GameFacade gameFacade, GameManagerFacade gameManagerFacade) {
        this.gameManagerFacade = gameManagerFacade;
    }

    // /{gameName}
    @PostMapping("{gameName}")
    public ResponseEntity<GameDto> createGame(@PathVariable String gameName) {
        GameDto newGame = gameManagerFacade.createGame(gameName);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @GetMapping("/start/{gameId}")
    public ResponseEntity<GameWithStartDateDto> startGame(@PathVariable int gameId) {
        GameWithStartDateDto startedGame = gameManagerFacade.startGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(startedGame);
    }

    @GetMapping("/end/{gameId}")
    public ResponseEntity<GameWithFinishDateDto> endGame(@PathVariable int gameId) {
        GameWithFinishDateDto finishedGame = gameManagerFacade.endGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(finishedGame);
    }

    @GetMapping("/add-player/{gameId}/{playerId}")
    public ResponseEntity<GameWithPlayersDto> addPlayerToGame(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
//        GameWithPlayersDto game = gameFacade.addPlayerToGame(gameId, playerId);
//        return ResponseEntity.status(HttpStatus.OK).body(game);
        return null;
    }

    @GetMapping("/remove-player/{gameId}/{playerId}")
    public ResponseEntity<GameWithPlayersDto> removePlayerFromGame(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
//        GameWithPlayersDto game = gameFacade.removePlayerFromGame(gameId, playerId);
//        return ResponseEntity.status(HttpStatus.OK).body(game);
        return null;
    }


    @PostMapping("/update-game-score/{gameId}")
    public ResponseEntity<GameResultDto> updateGameScore(@PathVariable int gameId, @RequestBody List<PlayerScore> gameScore) {
            GameResultDto result = gameManagerFacade.saveGameScore(gameId, gameScore);
            return ResponseEntity.ok().body(result);
    }

}

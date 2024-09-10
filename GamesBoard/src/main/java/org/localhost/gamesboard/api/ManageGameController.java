package org.localhost.gamesboard.api;
import org.localhost.gamesboard.Dto.GameResultDto;
import org.localhost.gamesboard.Dto.GameWithFinishDateDto;
import org.localhost.gamesboard.Dto.GameWithPlayersDto;
import org.localhost.gamesboard.Dto.GameWithStartDateDto;
import org.localhost.gamesboard.facade.GameFacade;
import org.localhost.gamesboard.model.PlayerScore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/manage-game")
public class ManageGameController {
    private final GameFacade gameFacade;

    public ManageGameController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @GetMapping("start/{gameId}")
    public ResponseEntity<GameWithStartDateDto> startGame(@PathVariable int gameId) {
        GameWithStartDateDto startedGame = gameFacade.startGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(startedGame);
    }

    @GetMapping("end/{gameId}")
    public ResponseEntity<GameWithFinishDateDto> endGame(@PathVariable int gameId) {
        GameWithFinishDateDto finishedGame = gameFacade.endGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(finishedGame);
    }

    @GetMapping("add-player/{gameId}/{playerId}")
    public ResponseEntity<GameWithPlayersDto> addPlayerToGame(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
        GameWithPlayersDto game = gameFacade.addPlayerToGame(gameId, playerId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @GetMapping("remove-player/{gameId}/{playerId}")
    public ResponseEntity<GameWithPlayersDto> removePlayerFromGame(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
        GameWithPlayersDto game = gameFacade.removePlayerFromGame(gameId, playerId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }


    @PostMapping("updateGameScore/{gameId}")
    public ResponseEntity<GameResultDto> updateGameScore(@PathVariable int gameId, @RequestBody List<PlayerScore> gameScore) {
            GameResultDto result = gameFacade.saveGameScore(gameId, gameScore);
            return ResponseEntity.ok().body(result);
    }

}

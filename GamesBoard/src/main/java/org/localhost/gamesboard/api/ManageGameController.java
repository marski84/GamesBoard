package org.localhost.gamesboard.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.localhost.gamesboard.Dto.GameWithFinishDateDto;
import org.localhost.gamesboard.Dto.GameWithStartDateDto;
import org.localhost.gamesboard.facade.GameFacade;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.PlayerScore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/manage-game")
public class ManageGameController {
    private final GameFacade gameFacade;

    public ManageGameController(GameFacade gameFacade, ObjectMapper objectMapper) {
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
    public ResponseEntity<Game> addPlayerToGame(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
        Game game = gameFacade.addPlayerToGame(gameId, playerId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @GetMapping("remove-player/{gameId}/{playerId}")
    public ResponseEntity<Game> removePlayerFromGame(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
        Game game = gameFacade.removePlayerFromGame(gameId, playerId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }


    @PostMapping("updateGameScore/{gameId}")
    public ResponseEntity<List<PlayerScore>> updateGameScore(@PathVariable int gameId, @RequestBody List<PlayerScore> gameScore) {

            return ResponseEntity.ok().body(gameScore); //

    }

}

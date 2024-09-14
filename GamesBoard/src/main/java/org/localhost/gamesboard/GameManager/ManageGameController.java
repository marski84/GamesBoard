package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.Dto.GameResultDto;
import org.localhost.gamesboard.Dto.GameWithFinishDateDto;
import org.localhost.gamesboard.Dto.GameWithStartDateDto;
import org.localhost.gamesboard.model.PlayerScore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * root:
 * - domena-x -> DomenaXApiInterface
 * - domena-y -> DomenaYApiInterface
 * - infra
 * - DomenaXYFacade
 */
@RestController
@RequestMapping("/manage-game/")
public class ManageGameController {
    private final GameManagerFacade gameManagerFacade;

    public ManageGameController(GameManagerFacade gameManagerFacade) {
        this.gameManagerFacade = gameManagerFacade;
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

    @PostMapping("/update-game-score/{gameId}")
    public ResponseEntity<GameResultDto> updateGameScore(@PathVariable int gameId, @RequestBody List<PlayerScore> gameScore) {
        GameResultDto result = gameManagerFacade.saveGameScore(gameId, gameScore);
        return ResponseEntity.ok().body(result);
    }

}

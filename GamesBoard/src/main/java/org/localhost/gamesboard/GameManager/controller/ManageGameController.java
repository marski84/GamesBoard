package org.localhost.gamesboard.GameManager.controller;


import org.localhost.gamesboard.Aggregate.service.AggregateService;
import org.localhost.gamesboard.Game.dto.GameDto;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.GameManager.model.PlayerScore;
import org.localhost.gamesboard.GameManager.service.GameManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage-game/")
public class ManageGameController {
    private final GameManagerService gameManagerService;
    private final AggregateService aggregateService;

    public ManageGameController(GameManagerService gameManagerService, AggregateService aggregateService) {
        this.gameManagerService = gameManagerService;
        this.aggregateService = aggregateService;
    }

    @GetMapping("/start/{gameId}")
    public ResponseEntity<GameDto> startGame(@PathVariable int gameId) {
        Game startedGame = gameManagerService.startGame(gameId);

        GameDto startedGameDto = GameDto.builder()
                .gameName(startedGame.getGameName())
                .startDate(startedGame.getGameStartDate())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(startedGameDto);
    }

    @GetMapping("/end/{gameId}")
    public ResponseEntity<GameDto> endGame(@PathVariable int gameId) {
        Game finishedGame = gameManagerService.endGame(gameId);

        GameDto finishedGameDto = GameDto.builder()
                .gameName(finishedGame.getGameName())
                .startDate(finishedGame.getGameStartDate())
                .finishDate(finishedGame.getGameFinishDate())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(finishedGameDto);
    }

    @PutMapping("/update-game-score/{gameId}")
    public ResponseEntity<GameDto> updateGameScore(@PathVariable int gameId,
                                                   @RequestBody List<PlayerScore> gameScore) {
        Game result = gameManagerService.saveGameScore(gameId, gameScore);

        GameDto updatedGameDto = GameDto.fromGame(result);
        return ResponseEntity.ok().body(updatedGameDto);
    }


    @PatchMapping("/register-player/{gameId}/{playerId}")
    public ResponseEntity<GameDto> registerPlayer(@PathVariable int gameId, @PathVariable int playerId) {
        Game gameDto = aggregateService.registerPlayerOnTheGame(gameId, playerId);

        GameDto registeredGameDto = GameDto.fromGame(gameDto);

        return ResponseEntity.ok(registeredGameDto);
    }

    @PatchMapping("/unregister-player/{gameId}/{playerId}")
    public ResponseEntity<GameDto> unregisterPlayer(@PathVariable int gameId, @PathVariable int playerId) {
        Game gameDto = aggregateService.unregisterPlayerFromTheGame(gameId, playerId);

        GameDto registeredGameDto = GameDto.fromGame(gameDto);

        return ResponseEntity.ok(registeredGameDto);
    }

}

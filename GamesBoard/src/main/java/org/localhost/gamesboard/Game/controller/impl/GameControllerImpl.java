package org.localhost.gamesboard.Game.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.Game.controller.GameController;
import org.localhost.gamesboard.Game.dto.GameDto;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.Game.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/games")
public class GameControllerImpl implements GameController {
    private final GameService gameService;

    public GameControllerImpl(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/{gameId}")
    public ResponseEntity<GameDto> getGame(@PathVariable int gameId) {
        Game game = gameService.getGameById(gameId);

        GameDto gameDto = GameDto.builder()
                .gameName(game.getGameName())
                .creationDate(game.getCreatedAt())
                .startDate(game.getGameStartDate())
                .finishDate(game.getGameFinishDate())
                .players(game.getPlayers())
                .playerScores(game.getGameScore())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(gameDto);
    }

    @GetMapping("/get-game-by-name/{gameName}")
    public ResponseEntity<GameDto> getGame(@PathVariable String gameName) {
        Game game = gameService.getGameByName(gameName);

        GameDto gameDto = GameDto.fromGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(gameDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        List<GameDto> gameDtoList = games.stream()
                .map(GameDto::fromGame)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(gameDtoList);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable int gameId) {
        Game game = gameService.deleteGame(gameId);

        GameDto deletedGameDto = GameDto.builder()
                .gameName(game.getGameName())
                .build();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create/{gameName}")
    public ResponseEntity<GameDto> createGame(
            @PathVariable String gameName
    ) {
        Game newGame = new Game();
        newGame.setGameName(gameName);

        Game createdGame = gameService.createGame(newGame);
        GameDto gameDto = GameDto.fromGame(createdGame);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameDto);

    }
}

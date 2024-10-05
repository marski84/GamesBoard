package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.Game.dto.GameDtoBuilder;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.GameManager.model.PlayerScore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseGameManagerHandler implements GameManagerHandler {
    private final GameManagerService gameManagerService;

    public BaseGameManagerHandler(GameManagerService gameManagerService) {
        this.gameManagerService = gameManagerService;
    }

    @Override
    public GameDtoBuilder startGame(int gameId) {
        Game game = gameManagerService.startGame(gameId);

        return GameDtoBuilder.builder()
                .gameName(game.getGameName())
                .startDate(game.getGameStartDate())
                .creationDate(game.getCreatedAt())
                .build();
    }

    @Override
    public GameDtoBuilder endGame(int gameId) {
        Game game = gameManagerService.endGame(gameId);

        return GameDtoBuilder.builder()
                .gameName(game.getGameName())
                .creationDate(game.getCreatedAt())
                .finishDate(game.getGameFinishDate())
                .build();
    }

    @Override
    public GameDtoBuilder saveGameScore(int gameId, List<PlayerScore> gameScore) {
        Game game = gameManagerService.saveGameScore(gameId, gameScore);

        return GameDtoBuilder.builder()
                .gameName(game.getGameName())
                .creationDate(game.getCreatedAt())
                .playerScores(game.getGameScore())
                .build();
    }

    public boolean isGameActive(int gameId) {
        return gameManagerService.isGameActive(gameId);
    }

    @Override
    public List<Player> getPlayersRegisteredInGame(int gameId) {
        return gameManagerService.getPlayersInGame(gameId);
    }

    @Override
    public boolean isPlayerRegisteredInGame(int gameId, int playerId) {
        return gameManagerService.isPlayerInGame(gameId, playerId);
    }
}

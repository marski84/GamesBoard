package org.localhost.gamesboard.Game.dto;

import org.localhost.gamesboard.Game.model.Game;

public final class GameDtoUtils {
    private GameDtoUtils() {
    }

    public static GameDto createGameDtoFromEntity(Game game) {
        return GameDto.builder()
                .gameName(game.getGameName())
                .creationDate(game.getCreatedAt())
                .startDate(game.getGameStartDate())
                .finishDate(game.getGameFinishDate())
                .players(game.getPlayers())
                .playerScores(game.getGameScore())
                .build();
    }
}

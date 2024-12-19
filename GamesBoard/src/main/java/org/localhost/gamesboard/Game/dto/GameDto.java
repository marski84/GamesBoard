package org.localhost.gamesboard.Game.dto;

import lombok.Builder;
import lombok.Getter;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.GameManager.model.PlayerScore;
import org.localhost.gamesboard.Player.model.Player;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class GameDto {
    private String gameName;
    private ZonedDateTime creationDate;
    private ZonedDateTime startDate;
    private ZonedDateTime finishDate;
    private List<PlayerScore> playerScores;
    private List<Player> players;

    public static GameDto fromGame(Game game) {
        return GameDto.builder()
                .gameName(game.getGameName())
                .creationDate(game.getCreatedAt())
                .startDate(game.getGameStartDate())
                .finishDate(game.getGameFinishDate())
                .playerScores(new ArrayList<>(game.getGameScore()))
                .players(new ArrayList<>(game.getPlayers()))
                .build();
    }


}
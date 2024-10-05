package org.localhost.gamesboard.Game.dto;

import lombok.Builder;
import lombok.Getter;
import org.localhost.gamesboard.GameManager.model.PlayerScore;
import org.localhost.gamesboard.Player.model.Player;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class GameDtoBuilder {
    private String gameName;
    private Instant creationDate;
    private Instant startDate;
    private Instant finishDate;
    private List<PlayerScore> playerScores;
    private List<Player> players;
}

package org.localhost.gamesboard.Player;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.exceptions.PlayerException;
import org.localhost.gamesboard.exceptions.messages.PlayerErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class BasePlayerService implements PlayerService {
    private final PlayerRepository playerRepository;

    public BasePlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Player registerPlayer(String playerName) {
        if (playerName == null) {
            log.error("Player name cannot be null");
            throw new IllegalArgumentException("Player cannot be null");
        }

        if (playerRepository.existsPlayerByPlayerNickname(playerName)) {
            log.error("Player with name {} already exists", playerName);
            throw new PlayerException(PlayerErrorCode.PLAYER_NICKNAME_EXISTS);
        }
        Player player = new Player();
        player.setPlayerNickname(playerName);
        return playerRepository.save(player);
    }


    @Transactional
    public Player removePlayer(int playerId) throws PlayerException {
        if (playerId < 0) {
            log.error("Player id cannot be negative");
            throw new IllegalArgumentException("Player id cannot be negative");
        }


        return playerRepository.findById(playerId)
                .map(player -> {
                    playerRepository.deleteById(playerId);
                    return player;
                })
                .orElseThrow(
                        () -> {
                            log.error("Player with id {} not found", playerId);
                            return new PlayerException(PlayerErrorCode.PLAYER_NOT_FOUND);
                        }
                );
    }

    @Override
    public Player getPlayerData(int playerId) {
        if (playerId < 0) {
            throw new IllegalArgumentException("Player id cannot be negative");
        }
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerException(PlayerErrorCode.PLAYER_NOT_FOUND));
    }
    @Override
    public Player getPlayerData(String playerName) {
        if (playerName == null || ObjectUtils.isEmpty(playerName)) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        return playerRepository.findPlayerByPlayerNickname(playerName)
                .orElseThrow(
                        () -> {
                            log.error("Player with name {} not found", playerName);
                            return new PlayerException(PlayerErrorCode.PLAYER_NOT_FOUND);
                        }
                );
    }



}

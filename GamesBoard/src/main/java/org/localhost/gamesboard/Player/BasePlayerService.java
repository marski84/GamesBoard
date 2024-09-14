package org.localhost.gamesboard.Player;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.exceptions.PlayerNotFoundException;
import org.localhost.gamesboard.exceptions.PlayerWithNicknameAlreadyExistException;
import org.localhost.gamesboard.model.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new PlayerWithNicknameAlreadyExistException();
        }
        Player player = new Player();
        player.setPlayerNickname(playerName);
        return playerRepository.save(player);
    }


    @Transactional
    public Player removePlayer(int playerId) throws PlayerNotFoundException {
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
                            return new PlayerNotFoundException("No player found with id: " + playerId);
                        }
                );
    }

    @Override
    public Player getPlayerData(int playerId) {
        if (playerId < 0) {
            throw new IllegalArgumentException("Player id cannot be negative");
        }
        return playerRepository.findById(playerId).orElseThrow(
                () -> {
                    log.error("Player with id {} not found", playerId);
                    return new PlayerNotFoundException("No player found with id: " + playerId);
                }
        );
    }

    public Player getPlayerData(String playerName) {
        if (playerName == null || playerName.isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        return playerRepository.findPlayerByPlayerNickname(playerName)
                .orElseThrow(
                        () -> {
                            log.error("Player with name {} not found", playerName);
                            return new PlayerNotFoundException("Player not found");
                        }
                );
    }
}

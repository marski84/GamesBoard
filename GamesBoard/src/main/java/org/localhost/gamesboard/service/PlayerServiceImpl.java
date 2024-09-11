package org.localhost.gamesboard.service;

import org.localhost.gamesboard.exceptions.PlayerNotFoundException;
import org.localhost.gamesboard.exceptions.PlayerWithNicknameAlreadyExistException;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Player registerPlayer(String playerName) {
        if (playerName == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        if (playerRepository.existsPlayerByPlayerNickname(playerName)) {
            throw new PlayerWithNicknameAlreadyExistException();
        }
        Player player = new Player();
        player.setPlayerNickname(playerName);
        return playerRepository.save(player);
    }



    @Transactional
    public Player removePlayer(int playerId) throws PlayerNotFoundException {
        if (playerId < 0) {
            throw new IllegalArgumentException("Player id cannot be negative");
        }


        return playerRepository.findById(playerId)
                .map(player -> {
                    playerRepository.deleteById(playerId);
                    return player;
                })
                .orElseThrow(
                        () -> new PlayerNotFoundException("No player found with id: " + playerId)
                );
    }

    @Override
    public Player getPlayerData(int playerId) {
        if (playerId < 0) {
            throw new IllegalArgumentException("Player id cannot be negative");
        }
        return playerRepository.findById(playerId).orElseThrow(
                () -> new PlayerNotFoundException("No player found with id: " + playerId)
        );
    }

    public Player getPlayerData(String playerName) {
        if (playerName == null || playerName.isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        return playerRepository.findPlayerByPlayerNickname(playerName)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));
    }
}

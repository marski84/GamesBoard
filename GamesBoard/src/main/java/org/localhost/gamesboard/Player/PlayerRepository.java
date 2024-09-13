package org.localhost.gamesboard.Player;

import org.localhost.gamesboard.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Optional<Player> findPlayerByPlayerNickname(String name);
    Optional<Player> getPlayerById(int playerId);
    boolean existsPlayerByPlayerNickname(String playerNickname);
}

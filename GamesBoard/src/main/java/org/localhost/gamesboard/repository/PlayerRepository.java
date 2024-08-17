package org.localhost.gamesboard.repository;

import org.localhost.gamesboard.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Optional<Player> findByPlayerNickname(String name);
}

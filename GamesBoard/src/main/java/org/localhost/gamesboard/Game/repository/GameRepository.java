package org.localhost.gamesboard.Game.repository;

import org.localhost.gamesboard.Game.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    Optional<Game> findByGameName(String gameName);

    boolean existsGameByGameName(String gameName);
}

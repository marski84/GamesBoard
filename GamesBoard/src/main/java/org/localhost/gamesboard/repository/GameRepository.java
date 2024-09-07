package org.localhost.gamesboard.repository;

import org.localhost.gamesboard.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    Optional<Game> findByGameName(String gameName);

    boolean existsGameByGameName(String gameName);
}

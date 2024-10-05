package org.localhost.gamesboard.repository;

import org.localhost.gamesboard.Game.GameRepository;
import org.localhost.gamesboard.Game.model.Game;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryGameRepository implements GameRepository {
    private final HashMap<Integer, Game> games = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(0);


    @Override
    public Game save(Game game) {
        if (game.getId() == null) {
            int gameId = idGenerator.getAndIncrement();
            game.setId(gameId);
            games.put(gameId, game);
            return game;
        }
        games.put(game.getId(), game);
        return game;
    }

    @Override
    public <S extends Game> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Game> findById(Integer id) {
        return Optional.ofNullable(games.get(id));
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Game> findAll() {
        return games.values().stream().toList();
    }

    @Override
    public Iterable<Game> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer gameId) {
        games.remove(gameId);
    }

    @Override
    public void delete(Game game) {
        games.remove(game.getId());

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Game> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Game> findByGameName(String gameName) {
        return games.values().stream()
                .filter(game -> game.getGameName().equals(gameName))
                .findFirst();
    }

    @Override
    public boolean existsGameByGameName(String gameName) {
        return false;
    }

    public void clearData() {
        games.clear();
    }
}
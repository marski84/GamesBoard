package org.localhost.gamesboard.repository;

import org.localhost.gamesboard.model.Game;

import java.util.*;

public class InMemoryGameRepository implements GameRepository {
    private final List<Game> games = new ArrayList<>();

    @Override
    public Game save(Game game) {
        games.add(game);
        return game;
    }

    @Override
    public <S extends Game> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Game> findById(Integer id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Game> findAll() {
        return null;
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
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Game entity) {

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
        return games.stream()
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
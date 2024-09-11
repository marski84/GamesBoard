package org.localhost.gamesboard.repository;

import org.localhost.gamesboard.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryPlayerRepository implements PlayerRepository {
    List<Player> players = new ArrayList<>();

    @Override
    public Optional<Player> findPlayerByPlayerNickname(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Player> getPlayerById(int playerId) {
        return players.stream()
                .filter(player -> player.getId() == playerId)
                .findFirst();
    }

    @Override
    public boolean existsPlayerByPlayerNickname(String playerNickname) {
        return false;
    }

    @Override
    public Player save(Player player) {

        players.add(player);
        return player;
    }

    @Override
    public <S extends Player> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Player> findById(Integer playerId) {
        return players.stream()
                .filter(player -> player.getId() == playerId)
                .findFirst();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Player> findAll() {
        return null;
    }

    @Override
    public Iterable<Player> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer playerId) {
        Player player = findById(playerId).orElse(null);
        players.remove(player);

    }

    @Override
    public void delete(Player entity) {


    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Player> entities) {

    }

    @Override
    public void deleteAll() {

    }
}

package org.localhost.gamesboard.repository;

import org.localhost.gamesboard.Player.PlayerRepository;
import org.localhost.gamesboard.exceptions.PlayerNotFoundException;
import org.localhost.gamesboard.model.Player;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPlayerRepository implements PlayerRepository {
    private final HashMap<Integer, Player> players = new HashMap<>();
    private final AtomicInteger playerIdGenerator = new AtomicInteger(0);

    @Override
    public Optional<Player> findPlayerByPlayerNickname(String name) {
        return players.values().stream()
                .filter(player -> player.getPlayerNickname().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Player> getPlayerById(int playerId) {
        return Optional.ofNullable(players.get(playerId));
    }

    @Override
    public boolean existsPlayerByPlayerNickname(String playerNickname) {
        return players.values().stream()
                .anyMatch(player -> player.getPlayerNickname().equals(playerNickname));

    }

    @Override
    public Player save(Player player) {
        int playerId = playerIdGenerator.getAndIncrement();
        player.setId(playerId);
        players.put(playerId, player);
        return player;
    }

    @Override
    public <S extends Player> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Player> findById(Integer playerId) {
        return Optional.ofNullable(players.get(playerId));
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
        players.remove(player.getId());
    }

    @Override
    public void delete(Player player) {
        players.remove(player.getId());
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

package org.localhost.gamesboard.Player.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.Player.repository.PlayerRepository;
import org.localhost.gamesboard.Player.service.impl.BasePlayerService;
import org.localhost.gamesboard.exceptions.PlayerException;
import org.localhost.gamesboard.repository.InMemoryPlayerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasePlayerServiceTest {

    private PlayerService objectUnderTest;
    private PlayerRepository playerRepository;
    private Player testPlayer;
    private final String TEST_PLAYER_NAME = "testName";
    private final int NON_EXISTENT_PLAYER_ID = 100;


    @BeforeEach
    void setUp() {
        playerRepository = new InMemoryPlayerRepository();
        objectUnderTest = new BasePlayerService(playerRepository);

        testPlayer = new Player();
    }

    @Test
    @DisplayName("registerPlayer should add player")
    void registerPlayer() {
//        given
        testPlayer.setPlayerNickname(TEST_PLAYER_NAME);
//        when
        Player createdPlayer = objectUnderTest.registerPlayer(TEST_PLAYER_NAME);
//        then
        assertEquals(testPlayer.getPlayerNickname(), createdPlayer.getPlayerNickname());
    }

    @Test
    @DisplayName("registerPlayer should throw when player is null")
    void registerPlayerNullPlayer() {
        assertThrows(
                IllegalArgumentException.class,
                () -> objectUnderTest.registerPlayer(null)
        );
    }

    @Test
    @DisplayName("registerPlayer should throw when player was already registered")
    void registerPlayerMoreThanOneTime() {
//        given
        testPlayer.setPlayerNickname(TEST_PLAYER_NAME);
        objectUnderTest.registerPlayer(TEST_PLAYER_NAME);
//        when. then
        assertThrows(
                PlayerException.class,
                () -> objectUnderTest.registerPlayer(TEST_PLAYER_NAME)
        );
    }

    @Test
    @DisplayName("removePlayer should remove existing player")
    void removePlayer() {
//        given
        testPlayer = objectUnderTest.registerPlayer(TEST_PLAYER_NAME);
//        when
        objectUnderTest.removePlayer(testPlayer.getId());
//        then
        assertThrows(
                PlayerException.class,
                () -> objectUnderTest.getPlayerData(testPlayer.getId())
        );
    }

    @Test
    @DisplayName("removePlayer should throw when player id < 0")
    void removePlayerNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> objectUnderTest.removePlayer(-1)
        );
    }

    @Test
    @DisplayName("removePlayer should throw when player does not exist")
    void removePlayerWhenPlayerDoesNotExist() {
        assertThrows(
                PlayerException.class,
                () -> objectUnderTest.removePlayer(NON_EXISTENT_PLAYER_ID)
        );
    }

    @Test
    @DisplayName("getPlayer data should retunr data for existing player id")
    void getPlayerData() {
//        given
        testPlayer = objectUnderTest.registerPlayer(TEST_PLAYER_NAME);
//        when
        Player createdPlayer = objectUnderTest.getPlayerData(testPlayer.getId());
//        then
        assertEquals(testPlayer.getPlayerNickname(), createdPlayer.getPlayerNickname());
    }

    @Test
    @DisplayName("getPlayerData should throw when does not exist")
    void getPlayerDataWhenPlayerDoesNotExist() {
        assertThrows(
                PlayerException.class,
                () -> objectUnderTest.getPlayerData(NON_EXISTENT_PLAYER_ID)
        );
    }

    @Test
    @DisplayName("getPlayerData should throw when player id is < 0")
    void getPlayerIdNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> objectUnderTest.getPlayerData(-1)
        );
    }

    @Test
    @DisplayName("getPlayerData should return data when searching by player Nickname")
    void testGetPlayerData() {
//        given
        Player newTestPlayer = objectUnderTest.registerPlayer(TEST_PLAYER_NAME);
        System.out.println(testPlayer.getPlayerNickname());
//        when
        Player createdPlayer = objectUnderTest.getPlayerData(newTestPlayer.getPlayerNickname());
//        then
        assertEquals(newTestPlayer.getPlayerNickname(), createdPlayer.getPlayerNickname());
    }
}
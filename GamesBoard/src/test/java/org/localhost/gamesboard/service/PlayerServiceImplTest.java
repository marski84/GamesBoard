package org.localhost.gamesboard.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.localhost.gamesboard.exceptions.PlayerNotFoundException;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.repository.PlayerRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {
    private final int NOT_EXISTING_PLAYER_ID = 10;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl objectUnderTest;

    private Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new Player();
        testPlayer.setId(1);
        testPlayer.setPlayerNickname("Test Player");
    }

    @Test
    @DisplayName("addPlayer should add player")
    void addPlayer() {
//        given
        when(playerRepository.save(any(Player.class))).thenReturn(testPlayer);
//        when
        Player testResult = objectUnderTest.registerPlayer(testPlayer.getPlayerNickname());
//        then
        assertEquals(testPlayer.getPlayerNickname(), testResult.getPlayerNickname());
    }

    @Test
    @DisplayName("addPlayer should throw when trying to add null as player")
    void addNullPlayer() {
        assertThrows(IllegalArgumentException.class, () -> objectUnderTest.registerPlayer(null));
    }

    @Test
    @DisplayName("removePlayer should remove player if player exist")
    void removePlayer() {
//        given
        when(playerRepository.findById(1)).thenReturn(Optional.of(testPlayer));
//        when
        Player removedPlayer = objectUnderTest.removePlayer(testPlayer.getId());
//        then
        assertEquals(testPlayer.getPlayerNickname(), removedPlayer.getPlayerNickname());
        verify(playerRepository).deleteById(testPlayer.getId());
    }

    @Test
    @DisplayName("removePlayer should throw when trying to remove player which does not exist")
    void removePlayerNotFound() {
//        given
        when(playerRepository.findById(NOT_EXISTING_PLAYER_ID)).thenReturn(Optional.empty());
//        when
        assertThrows(PlayerNotFoundException.class, () -> objectUnderTest.removePlayer(NOT_EXISTING_PLAYER_ID));
//        then
    }

    @Test
    @DisplayName("removePlayer should throw when player id is < 0")
    void removePlayerIdNegative() {
//        given, when, then
        assertThrows(IllegalArgumentException.class, () -> objectUnderTest.removePlayer(-1));
    }

    @Test
    @DisplayName("getPlayerById should return existing player")
    void getPlayerById() {
//        given
        when(playerRepository.findById(testPlayer.getId())).thenReturn(Optional.of(testPlayer));
//        when
        Player testResult = objectUnderTest.getPlayerData(testPlayer.getId());
//        then
        assertEquals(testPlayer.getPlayerNickname(), testResult.getPlayerNickname());
        verify(playerRepository).findById(testPlayer.getId());
    }

    @Test
    @DisplayName("getPlayerById should throw when player is not found")
    void getPlayerByIdNotFound() {
//        given
        when(playerRepository.findById(NOT_EXISTING_PLAYER_ID)).thenReturn(Optional.empty());
//        when, then
        assertThrows(PlayerNotFoundException.class, () -> objectUnderTest.getPlayerData(NOT_EXISTING_PLAYER_ID));
        verify(playerRepository).findById(NOT_EXISTING_PLAYER_ID);
    }

    @Test
    @DisplayName("getPlayerById should throw when player id is < 0")
    void ThrowWhenPlayerIdNegative() {
//        given, when, then
        assertThrows(IllegalArgumentException.class, () -> objectUnderTest.getPlayerData(-1));
    }


}
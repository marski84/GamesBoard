package org.localhost.gamesboard.exceptions;

public class PlayerAlreadyInGameException extends RuntimeException {
    public PlayerAlreadyInGameException() {
        super("Player is already in a game!");
    }
}

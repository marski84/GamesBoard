package org.localhost.gamesboard.exceptions;

public class PlayerNotRegisteredInGameException extends RuntimeException {
    public PlayerNotRegisteredInGameException() {
        super("Player is not registered for this game.");
    }
}

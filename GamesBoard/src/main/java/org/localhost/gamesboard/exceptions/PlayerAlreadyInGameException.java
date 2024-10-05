package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.ExceptionMessages;

public class PlayerAlreadyInGameException extends RuntimeException {
    public PlayerAlreadyInGameException() {
        super(ExceptionMessages.PLAYER_ALREADY_REGISTERED);
    }
}

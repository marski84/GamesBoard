package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.ExceptionMessages;

public class PlayerNotRegisteredInGameException extends RuntimeException {
    public PlayerNotRegisteredInGameException() {
        super(ExceptionMessages.PLAYER_NOT_REGISTERED);
    }
}

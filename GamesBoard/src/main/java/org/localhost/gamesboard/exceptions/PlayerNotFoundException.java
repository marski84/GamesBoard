package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.ExceptionMessages;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException() {
        super(ExceptionMessages.PLAYER_NOT_FOUND);
    }
}

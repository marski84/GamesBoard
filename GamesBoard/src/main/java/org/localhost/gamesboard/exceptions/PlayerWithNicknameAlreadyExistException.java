package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.ExceptionMessages;

public class PlayerWithNicknameAlreadyExistException extends RuntimeException {
    public PlayerWithNicknameAlreadyExistException() {
        super(ExceptionMessages.PLAYER_WITH_NICKNAME_EXISTS);
    }
}

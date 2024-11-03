package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.PlayerErrorCode;

public class PlayerException extends RuntimeException {
    private final PlayerErrorCode errorCode;

    public PlayerException(PlayerErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    public int getCode() {
        return errorCode.getCode();
    }
}

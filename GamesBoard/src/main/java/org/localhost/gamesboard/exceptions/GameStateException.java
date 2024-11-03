package org.localhost.gamesboard.exceptions;

import lombok.Getter;
import org.localhost.gamesboard.exceptions.messages.GameErrorCode;

@Getter
public class GameStateException extends RuntimeException {
    private final GameErrorCode errorCode;

    public GameStateException(GameErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    public int getCode() {
        return errorCode.getCode();
    }
}

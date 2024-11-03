package org.localhost.gamesboard.exceptions.messages;

public enum GameErrorCode {
    GAME_ALREADY_EXISTS(1000, "Game with given ID already exists"),
    GAME_ALREADY_FINISHED(1001, "Game is already finished"),
    GAME_ALREADY_STARTED(1002, "Game is already started"),
    GAME_NOT_ACTIVE(1003, "Game is not active"),
    GAME_NOT_FOUND(1004, "Game not found"),
    GAME_NOT_STARTED(1005, "Game has not started yet");

    private final int code;
    private final String defaultMessage;

    GameErrorCode(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public int getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}

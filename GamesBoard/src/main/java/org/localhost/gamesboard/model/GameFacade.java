package org.localhost.gamesboard.model;

public interface GameFacade {
    void createGame(String gameName);
    void startGame(int gameId);
    void endGame(int gameId);
    void addPlayer(String playerName);
    void removePlayer(String playerName);
    void saveGameScore(int gameId, String gameScore);
    int getGameId(String gameName);
    Game getGameById(int gameId);

}

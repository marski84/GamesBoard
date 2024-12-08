-- Najpierw drop tabel
DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS games;

-- Tworzenie tabel
CREATE TABLE games
(
    id               INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    game_name        VARCHAR(255) NOT NULL,
    game_start_date  TIMESTAMP,
    game_finish_date TIMESTAMP,
    game_score       VARCHAR(8000),
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE players
(
    id              INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    player_nickname VARCHAR(255) NOT NULL,
    game_id         INT,
    created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_game
        FOREIGN KEY (game_id)
            REFERENCES games (id)
            ON DELETE SET NULL,

    CONSTRAINT uk_player_nickname_per_game
        UNIQUE (player_nickname, game_id)
);

-- Podstawowe indeksy
CREATE INDEX idx_games_dates ON games (created_at, game_start_date, game_finish_date);
CREATE INDEX idx_game_name ON games (game_name);
CREATE INDEX idx_player_nickname ON players (player_nickname);
CREATE INDEX idx_player_game ON players (game_id);

-- Proste inserty dla gier
INSERT INTO games (game_name, created_at)
VALUES ('Chess Game', CURRENT_TIMESTAMP),
       ('Monopoly Night', CURRENT_TIMESTAMP);

INSERT INTO games (game_name, game_start_date, created_at)
VALUES ('Active Poker', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Active Chess Tournament', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO games (game_name, game_start_date, game_finish_date, created_at)
VALUES ('Finished Scrabble', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO games (game_name, game_start_date, game_score, created_at)
VALUES ('Tournament Game',
        CURRENT_TIMESTAMP,
        '[{"playerNickname":"Player1","score":100},{"playerNickname":"Player2","score":150}]',
        CURRENT_TIMESTAMP);

-- Inserty dla graczy
INSERT INTO players (player_nickname, game_id)
VALUES ('ChessPlayer1', 1),
       ('ChessPlayer2', 1),
       ('MonopolyMaster', 2),
       ('MonopolyKing', 2),
       ('PokerPro1', 3),
       ('PokerPro2', 3),
       ('ChessMaster1', 4),
       ('ScrabbleKing', 5),
       ('Player1', 6),
       ('Player2', 6);

-- Gracze bez gry
INSERT INTO players (player_nickname)
VALUES ('WaitingPlayer1'),
       ('WaitingPlayer2'),
       ('WaitingPlayer3');
-- Najpierw drop tabel
DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS games;

-- Tworzenie tabel
CREATE TABLE games
(
    id               SERIAL PRIMARY KEY,
    game_name        VARCHAR(255)             NOT NULL,
    game_start_date  TIMESTAMP WITH TIME ZONE,
    game_finish_date TIMESTAMP WITH TIME ZONE,
    game_score       JSONB,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT check_game_dates CHECK (
        (game_start_date IS NULL OR game_start_date >= created_at)
            AND
        (game_finish_date IS NULL OR game_finish_date >= created_at)
            AND
        (game_finish_date IS NULL OR game_start_date IS NULL OR game_finish_date >= game_start_date)
        )
);

CREATE TABLE players
(
    id              SERIAL PRIMARY KEY,
    player_nickname VARCHAR(255)             NOT NULL,
    game_id         INTEGER,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

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

-- Test data
INSERT INTO games (id, game_name, game_start_date, game_finish_date, created_at, game_score)
VALUES (1, 'Test Game 1', NULL, NULL, '2024-01-01 10:00:00+00', NULL),
       (2, 'Test Game 2', '2024-01-01 10:30:00+00', NULL, '2024-01-01 10:00:00+00', NULL),
       (3, 'Test Game 3', '2024-01-01 10:30:00+00', '2024-01-01 11:30:00+00', '2024-01-01 10:00:00+00', NULL),
       (4, 'Test Game 4', NULL, NULL, '2024-01-01 10:00:00+00', NULL),
       (5, 'Test Game 5', '2024-01-01 10:30:00+00', NULL, '2024-01-01 10:00:00+00',
        '[
          {
            "playerNickname": "TestPlayer1",
            "score": 100
          },
          {
            "playerNickname": "TestPlayer2",
            "score": 150
          }
        ]'::jsonb),
       (6, 'Test Game 6', NULL, NULL, '2024-01-01 10:00:00+00', NULL);

-- Test data: players
INSERT INTO players (id, player_nickname, game_id, created_at)
VALUES (1, 'TestPlayer1', 1, '2024-01-01 10:00:00+00'),
       (2, 'TestPlayer2', 1, '2024-01-01 10:00:00+00'),
       (3, 'TestPlayer3', 2, '2024-01-01 10:00:00+00'),
       (4, 'TestPlayer4', 2, '2024-01-01 10:00:00+00'),
       (5, 'TestPlayer5', 3, '2024-01-01 10:00:00+00'),
       (6, 'TestPlayer6', 3, '2024-01-01 10:00:00+00'),
       (7, 'TestPlayer7', 5, '2024-01-01 10:00:00+00'),
       (8, 'TestPlayer8', 5, '2024-01-01 10:00:00+00'),
       (9, 'TestPlayer9', NULL, '2024-01-01 10:00:00+00'),
       (10, 'TestPlayer10', 6, '2024-01-01 10:00:00+00');


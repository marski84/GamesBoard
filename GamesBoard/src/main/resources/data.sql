CREATE TABLE games
(
    id               SERIAL PRIMARY KEY UNIQUE,
    game_name        VARCHAR(255)             NOT NULL,
    game_start_date  TIMESTAMP WITH TIME ZONE,
    game_finish_date TIMESTAMP WITH TIME ZONE,
    game_score       JSONB,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Constraint sprawdzający kolejność dat
    CONSTRAINT check_game_dates CHECK (
        (game_start_date IS NULL OR game_start_date >= created_at)
            AND
        (game_finish_date IS NULL OR game_finish_date >= created_at)
            AND
        (game_finish_date IS NULL OR game_start_date IS NULL OR game_finish_date >= game_start_date)
        )
);
-- Indeksy dla wydajności
CREATE INDEX idx_games_ids ON games (id);
CREATE INDEX idx_game_name ON games (game_name);


-- players
CREATE TABLE players
(
    id              SERIAL PRIMARY KEY UNIQUE,
    player_nickname VARCHAR(255)             NOT NULL UNIQUE,
    game_id         INTEGER UNIQUE,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Relacja do tabeli games
    CONSTRAINT fk_game
        FOREIGN KEY (game_id)
            REFERENCES games (id)
            ON DELETE SET NULL, -- lub ON DELETE CASCADE, zależnie od wymagań


    -- Constraint zapewniający, że nickname nie jest pusty
    CONSTRAINT check_player_nickname
        CHECK (player_nickname IS NOT NULL AND LENGTH(TRIM(player_nickname)) > 0)
);

-- Indeksy dla optymalizacji wyszukiwania
CREATE INDEX idx_player_nickname ON players (player_nickname);
CREATE INDEX idx_player_game ON players (game_id);


-- Najpierw usuńmy constraint jeśli istnieje
ALTER TABLE players
    DROP CONSTRAINT IF EXISTS players_game_id_key;

-- Podstawowe inserty dla gier
INSERT INTO games (game_name, created_at)
VALUES ('Chess Game 1', CURRENT_TIMESTAMP),
       ('Monopoly Night', CURRENT_TIMESTAMP),
       ('Poker Evening', CURRENT_TIMESTAMP);

-- Gry ze start date
INSERT INTO games (game_name, created_at, game_start_date)
VALUES ('Active Chess', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Active Monopoly', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + interval '1 minute');

-- Gry zakończone
INSERT INTO games (game_name, created_at, game_start_date, game_finish_date)
VALUES ('Finished Chess',
        CURRENT_TIMESTAMP - interval '2 hour',
        CURRENT_TIMESTAMP - interval '1 hour',
        CURRENT_TIMESTAMP);

-- Transakcyjne dodawanie graczy do gier
DO
$$
    BEGIN
        -- Dodanie graczy do Chess Game 1
        INSERT INTO players (player_nickname, game_id)
        VALUES ('ChessPlayer1', (SELECT id FROM games WHERE game_name = 'Chess Game 1')),
               ('ChessPlayer2', (SELECT id FROM games WHERE game_name = 'Chess Game 1'));

        -- Dodanie gracza do Monopoly Night
        INSERT INTO players (player_nickname, game_id)
        VALUES ('MonopolyMaster', (SELECT id FROM games WHERE game_name = 'Monopoly Night'));

        -- Dodanie gracza do Poker Evening
        INSERT INTO players (player_nickname, game_id)
        VALUES ('PokerKing', (SELECT id FROM games WHERE game_name = 'Poker Evening'));
    END
$$;

-- Gracze bez przypisanej gry
INSERT INTO players (player_nickname)
VALUES ('WaitingPlayer1'),
       ('WaitingPlayer2');

-- Dodanie gry turniejowej z graczami
WITH new_game AS (
    INSERT INTO games (
                       game_name,
                       created_at,
                       game_start_date,
                       game_score
        )
        VALUES ('Tournament Final',
                CURRENT_TIMESTAMP,
                CURRENT_TIMESTAMP,
                jsonb_build_array(
                        jsonb_build_object('playerId', 1, 'score', 100),
                        jsonb_build_object('playerId', 2, 'score', 150)
                ))
        RETURNING id)
INSERT
INTO players (player_nickname, game_id)
SELECT unnest(ARRAY ['Finalist1', 'Finalist2']), id
FROM new_game;

-- Dodanie unikalnego constraintu na nickname w ramach gry
ALTER TABLE players
    ADD CONSTRAINT unique_player_nickname_per_game
        UNIQUE (player_nickname, game_id);

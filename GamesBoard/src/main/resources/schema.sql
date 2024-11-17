CREATE TABLE games
(
    id               SERIAL PRIMARY KEY UNIQUE,
    game_name        VARCHAR(255)             NOT NULL,
    game_start_date  TIMESTAMP WITH TIME ZONE UNIQUE,
    game_finish_date TIMESTAMP WITH TIME ZONE UNIQUE,
    game_score       JSONB,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Constraint sprawdzający kolejność dat
    CONSTRAINT check_game_dates CHECK (
        (game_start_date IS NULL OR game_start_date >= created_at)
            AND
        (game_finish_date IS NULL OR game_finish_date >= created_at)
            AND
        (game_finish_date IS NULL OR game_start_date IS NULL OR game_finish_date >= game_start_date)
        ),

    -- Indeksy dla optymalizacji
    CONSTRAINT uk_game_start_date UNIQUE (game_start_date),
    CONSTRAINT uk_game_finish_date UNIQUE (game_finish_date)
);
-- Indeksy dla wydajności
CREATE INDEX idx_games_ids ON games (id);
CREATE INDEX idx_games_dates ON games (created_at, game_start_date, game_finish_date);
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

    -- Constraint zapewniający unikalność nicku w ramach gry
    CONSTRAINT uk_player_nickname_per_game
        UNIQUE (player_nickname, game_id),

    -- Constraint zapewniający, że nickname nie jest pusty
    CONSTRAINT check_player_nickname
        CHECK (player_nickname IS NOT NULL AND LENGTH(TRIM(player_nickname)) > 0)
);

-- Indeksy dla optymalizacji wyszukiwania
CREATE INDEX idx_player_nickname ON players (player_nickname);
CREATE INDEX idx_player_game ON players (game_id);
-- Najpierw wstawiamy gry
INSERT INTO games (game_name, game_start_date, game_finish_date, game_score, created_at)
VALUES ('Chess Tournament #1',
        '2024-01-01 10:00:00+00',
        '2024-01-01 11:30:00+00',
        '[{"playerName": "GrandMaster123", "score": 1}, {"playerName": "ChessWizard", "score": 0}]',
        CURRENT_TIMESTAMP),

       ('Soccer Match #123',
        '2024-01-02 15:00:00+00',
        '2024-01-02 16:45:00+00',
        '[{"playerName": "Striker007", "score": 2}, {"playerName": "GoalKeeper99", "score": 0}, {"playerName": "Midfielder11", "score": 1}, {"playerName": "Defender22", "score": 0}]',
        CURRENT_TIMESTAMP),

       ('Poker Night',
        '2024-01-03 20:00:00+00',
        '2024-01-04 02:00:00+00',
        '[{"playerName": "PokerFace", "score": 1000}, {"playerName": "CardShark", "score": 750}, {"playerName": "BluffMaster", "score": 500}, {"playerName": "ChipStacker", "score": 250}]',
        CURRENT_TIMESTAMP),

       ('Basketball Game #45',
        '2024-01-05 18:00:00+00',
        '2024-01-05 19:30:00+00',
        '[{"playerName": "Hoops_King", "score": 32}, {"playerName": "Slam_Dunker", "score": 28}, {"playerName": "ThreePointer", "score": 22}]',
        CURRENT_TIMESTAMP),

       ('Chess Tournament #2',
        '2024-01-06 14:00:00+00',
        NULL,
        '[{"playerName": "ChessWizard", "score": 0}, {"playerName": "NewChallenger", "score": 0}]',
        CURRENT_TIMESTAMP),

       ('Mario Kart Tournament',
        '2024-01-07 13:00:00+00',
        '2024-01-07 17:00:00+00',
        '[{"playerName": "SpeedDemon", "score": 150}, {"playerName": "ShellThrower", "score": 120}, {"playerName": "RainbowWarrior", "score": 90}]',
        CURRENT_TIMESTAMP),

       ('Counter-Strike Match',
        '2024-01-08 21:00:00+00',
        '2024-01-08 22:30:00+00',
        '[{"playerName": "HeadshotPro", "score": 25}, {"playerName": "SniperElite", "score": 20}, {"playerName": "TacticalOps", "score": 18}]',
        CURRENT_TIMESTAMP),

       ('Tennis Singles',
        '2024-01-09 09:00:00+00',
        '2024-01-09 11:00:00+00',
        '[{"playerName": "ServeMaster", "score": 3}, {"playerName": "RallyKing", "score": 1}]',
        CURRENT_TIMESTAMP),

       ('Minecraft Building Contest',
        '2024-01-10 16:00:00+00',
        NULL,
        '[{"playerName": "BlockArchitect", "score": 0}, {"playerName": "CreativeMind", "score": 0}, {"playerName": "PixelArtist", "score": 0}]',
        CURRENT_TIMESTAMP),

       ('League of Legends Clash',
        '2024-01-11 19:00:00+00',
        '2024-01-11 21:30:00+00',
        '[{"playerName": "JungleKing", "score": 8}, {"playerName": "MidOrFeed", "score": 12}, {"playerName": "SupportLife", "score": 4}, {"playerName": "TopDiff", "score": 6}, {"playerName": "ADCarry", "score": 15}]',
        CURRENT_TIMESTAMP);

-- Następnie wstawiamy graczy powiązanych z grami
INSERT INTO players (player_nickname, game_id, created_at)
VALUES
    -- Chess Tournament #1 players
    ('GrandMaster123', 1, CURRENT_TIMESTAMP),
    ('ChessWizard', 1, CURRENT_TIMESTAMP),

    -- Soccer Match players
    ('Striker007', 2, CURRENT_TIMESTAMP),
    ('GoalKeeper99', 2, CURRENT_TIMESTAMP),
    ('Midfielder11', 2, CURRENT_TIMESTAMP),
    ('Defender22', 2, CURRENT_TIMESTAMP),

    -- Poker Night players
    ('PokerFace', 3, CURRENT_TIMESTAMP),
    ('CardShark', 3, CURRENT_TIMESTAMP),
    ('BluffMaster', 3, CURRENT_TIMESTAMP),
    ('ChipStacker', 3, CURRENT_TIMESTAMP),

    -- Basketball Game players
    ('Hoops_King', 4, CURRENT_TIMESTAMP),
    ('Slam_Dunker', 4, CURRENT_TIMESTAMP),
    ('ThreePointer', 4, CURRENT_TIMESTAMP),

    -- Chess Tournament #2 players
    ('ChessWizard', 5, CURRENT_TIMESTAMP),
    ('NewChallenger', 5, CURRENT_TIMESTAMP),

    -- Mario Kart Tournament players
    ('SpeedDemon', 6, CURRENT_TIMESTAMP),
    ('ShellThrower', 6, CURRENT_TIMESTAMP),
    ('RainbowWarrior', 6, CURRENT_TIMESTAMP),

    -- Counter-Strike Match players
    ('HeadshotPro', 7, CURRENT_TIMESTAMP),
    ('SniperElite', 7, CURRENT_TIMESTAMP),
    ('TacticalOps', 7, CURRENT_TIMESTAMP),

    -- Tennis Singles players
    ('ServeMaster', 8, CURRENT_TIMESTAMP),
    ('RallyKing', 8, CURRENT_TIMESTAMP),

    -- Minecraft Building Contest players
    ('BlockArchitect', 9, CURRENT_TIMESTAMP),
    ('CreativeMind', 9, CURRENT_TIMESTAMP),
    ('PixelArtist', 9, CURRENT_TIMESTAMP),

    -- League of Legends Clash players
    ('JungleKing', 10, CURRENT_TIMESTAMP),
    ('MidOrFeed', 10, CURRENT_TIMESTAMP),
    ('SupportLife', 10, CURRENT_TIMESTAMP),
    ('TopDiff', 10, CURRENT_TIMESTAMP),
    ('ADCarry', 10, CURRENT_TIMESTAMP),

    -- Wolni agenci
    ('FreeAgent1', NULL, CURRENT_TIMESTAMP),
    ('FreeAgent2', NULL, CURRENT_TIMESTAMP),
    ('PracticePlayer', NULL, CURRENT_TIMESTAMP);
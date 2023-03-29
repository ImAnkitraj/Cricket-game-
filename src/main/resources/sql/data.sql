--DROP TABLE ball;
--DROP TABLE match_over;
--DROP TABLE player;
--DROP TABLE player_inning_details;
--DROP TABLE inning;
--DROP TABLE cricket_match;
--DROP TABLE team;
--
CREATE TABLE team (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE cricket_match (
    id INT AUTO_INCREMENT NOT NULL,
    venue VARCHAR(50) NOT NULL,
    team1_id INT NOT NULL,
    team2_id INT NOT NULL,
    PRIMARY KEY(id),
);

CREATE TABLE inning (
    id INT AUTO_INCREMENT NOT NULL,
    match_id INT NOT NULL,
    batting_team INT NOT NULL,
    balling_team INT NOT NULL,
    is_completed BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(id),
);

CREATE TABLE player (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL,
    team_id int,
    type ENUM('BATSMAN', 'BALLER', 'ALLROUNDER') NOT NULL,
    PRIMARY KEY(id),
);

CREATE TABLE match_over (
    id INT AUTO_INCREMENT NOT NULL,
    over_no int NOT NULL,
    baller_id int NOT NULL,
    inning_id int NOT NULL,
    PRIMARY KEY(id),
);

CREATE TABLE ball (
    id INT AUTO_INCREMENT NOT NULL,
    outcome ENUM('NORUN', 'ONE', 'TWO', 'THREE', 'FOUR', 'SIX', 'WICKET') NOT NULL,
    ball_no int NOT NULL,
    baller_id int NOT NULL,
    batsman_id int NOT NULL,
    over_id int NOT NULL,
    PRIMARY KEY(id),
);

CREATE TABLE player_inning_details (
    id INT AUTO_INCREMENT NOT NULL,
    inning_id INT NOT NULL,
    runs INT DEFAULT 0,
    balls_faced INT DEFAULT 0,
    sixes INT DEFAULT 0,
    fours INT DEFAULT 0,
    wickets INT DEFAULT 0,
    runs_given INT DEFAULT 0,
    is_out BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id),
);

CREATE TABLE cricket_match_status (
    id INT AUTO_INCREMENT NOT NULL,
    match_id INT NOT NULL,
    current_inning INT,
    PRIMARY KEY (id),
);


CREATE TABLE inning_status (
    id INT AUTO_INCREMENT NOT NULL,
    inning_id INT NOT NULL,
    current_match_over INT,
    current_baller_id INT,
    current_striker_id INT,
    current_non_striker_id INT,
    PRIMARY KEY (id),
);
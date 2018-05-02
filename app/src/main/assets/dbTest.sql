--
-- File generated with SQLiteStudio v3.1.1 on mer. mai 2 17:38:34 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: quest
CREATE TABLE quest (general_id INTEGER PRIMARY KEY AUTOINCREMENT, theme TEXT NOT NULL, email_part TEXT REFERENCES user (email), quest_id NOT NULL);
INSERT INTO quest (general_id, theme, email_part, quest_id) VALUES (1, 'war', 'jf@gmail', 1);
INSERT INTO quest (general_id, theme, email_part, quest_id) VALUES (2, 'war', 'max@gmail', 1);
INSERT INTO quest (general_id, theme, email_part, quest_id) VALUES (3, 'food', 'jf@gmail', 2);
INSERT INTO quest (general_id, theme, email_part, quest_id) VALUES (4, 'food', 'max@gmail', 2);
INSERT INTO quest (general_id, theme, email_part, quest_id) VALUES (5, 'food', 'anto@gmail', 2);

-- Table: user
CREATE TABLE user (email TEXT PRIMARY KEY, nom TEXT NOT NULL, prenom TEXT NOT NULL);
INSERT INTO user (email, nom, prenom) VALUES ('jf@gmail', 'James', 'jf');
INSERT INTO user (email, nom, prenom) VALUES ('max@gmail', 'Georges', 'Max');
INSERT INTO user (email, nom, prenom) VALUES ('anto@gmail', 'Dupont', 'anto');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;

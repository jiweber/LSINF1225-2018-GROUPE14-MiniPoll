--
-- File generated with SQLiteStudio v3.1.1 on Mon May 7 14:29:56 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Dilemme
DROP TABLE IF EXISTS Dilemme;
CREATE TABLE Dilemme (IDdilemme INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, Auteur TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, Titre TEXT NOT NULL, Participant TEXT REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL, FOREIGN KEY (Auteur) REFERENCES Utilisateur (Mail));
INSERT INTO Dilemme (IDdilemme, Auteur, Titre, Participant) VALUES (1, 'harry.smith@mymail.com', 'O� vais-je manger ce soir?', 'gb@ucluvain.be');
INSERT INTO Dilemme (IDdilemme, Auteur, Titre, Participant) VALUES (2, 'gb@ucluvain.be', 'Quel pull est ce que j''ach�te', 'harry.smith@mymail.com');

-- Table: Participant_dilemme
DROP TABLE IF EXISTS Participant_dilemme;
CREATE TABLE Participant_dilemme (ID_proposition REFERENCES Proposition_dilemme (IDproposition) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL, Evaluation CHECK (evaluation IN ('j aime', 'je n aime pas')));

-- Table: Participation_questionnaire
DROP TABLE IF EXISTS Participation_questionnaire;
CREATE TABLE Participation_questionnaire (Mail TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, IDquestionnaire INTEGER NOT NULL REFERENCES Questionnaire (IDquestionnaire) ON DELETE CASCADE ON UPDATE CASCADE, Score INTEGER, PRIMARY KEY (Mail, IDquestionnaire), FOREIGN KEY (Mail) REFERENCES Utilisateur (Mail));
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score) VALUES ('a', 1, 2);
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score) VALUES ('LDV@uclouvain.be', 1, NULL);
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score) VALUES ('LDV@uclouvain.be', 2, 2);
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score) VALUES ('a', 2, 2);

-- Table: Participation_sondage
DROP TABLE IF EXISTS Participation_sondage;
CREATE TABLE Participation_sondage (Mail_participant TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, IDsondage INTEGER NOT NULL REFERENCES Sondage (IDsondage) ON DELETE CASCADE ON UPDATE CASCADE, IDchoix INT REFERENCES Proposition_sondage (IDsondage) ON DELETE CASCADE ON UPDATE CASCADE, Rang INT, FOREIGN KEY (Mail_participant) REFERENCES Utilisateur (Mail), PRIMARY KEY (IDsondage, Mail_participant));
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('harry.smith@mymail.com', 1, 21, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('adb@uclouvain.be', 1, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('gb@ucluvain.be', 1, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('jw@uclouvain.be', 2, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('gb@ucluvain.be', 2, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('harry.smith@mymail.com', 2, NULL, NULL);

-- Table: Proposition_dilemme
DROP TABLE IF EXISTS Proposition_dilemme;
CREATE TABLE Proposition_dilemme (IDdilemme TEXT NOT NULL REFERENCES Dilemme (IDdilemme) ON DELETE CASCADE ON UPDATE CASCADE, Sujet TEXT NOT NULL, Format TEXT NOT NULL CHECK (format IN ('txt', 'pic')), IDproposition TEXT PRIMARY KEY NOT NULL REFERENCES Proposition_dilemme (IDproposition) ON DELETE CASCADE ON UPDATE CASCADE);

-- Table: Proposition_sondage
DROP TABLE IF EXISTS Proposition_sondage;
CREATE TABLE Proposition_sondage (IDsondage INTEGER NOT NULL REFERENCES Sondage (IDsondage) ON DELETE CASCADE ON UPDATE CASCADE, Ennonce_de_la_proposition TEXT NOT NULL);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition) VALUES (1, 'Madrid');
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition) VALUES (1, 'Paris');
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition) VALUES (1, 'Londres');
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition) VALUES (2, 'Bob');
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition) VALUES (2, 'Francis');

-- Table: Question
DROP TABLE IF EXISTS Question;
CREATE TABLE Question (IDquestion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, IDquestionnaire TEXT NOT NULL REFERENCES Questionnaire (IDquestionnaire) ON DELETE CASCADE ON UPDATE CASCADE, Enonce TEXT NOT NULL);
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce) VALUES (1, '1', 'Quand fut d�couvert l''Am�rique ?');
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce) VALUES (2, '1', 'Cr�ation de la Belgique ?');
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce) VALUES (3, '2', '1+1=?');
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce) VALUES (4, '2', '3*6=?');
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce) VALUES (5, '1', 'Bataille de Hastings');

-- Table: Questionnaire
DROP TABLE IF EXISTS Questionnaire;
CREATE TABLE Questionnaire (IDquestionnaire INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, Titre TEXT NOT NULL, "Auteur " TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE);
INSERT INTO Questionnaire (IDquestionnaire, Titre, "Auteur ") VALUES (1, 'Histoire', 'harry.smith@mymail.com');
INSERT INTO Questionnaire (IDquestionnaire, Titre, "Auteur ") VALUES (2, 'Maths', 'harry.smith@mymail.com');

-- Table: Relation
DROP TABLE IF EXISTS Relation;
CREATE TABLE Relation (Utilisateur1 TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE MATCH FULL, Utilisateur2 TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, Statut TEXT NOT NULL DEFAULT 'En attente' CHECK (statut IN ('Ami', 'En attente', 'Rejet')), PRIMARY KEY (Utilisateur1, Utilisateur2), FOREIGN KEY (Utilisateur2) REFERENCES Utilisateur (Mail), FOREIGN KEY (Utilisateur1) REFERENCES Utilisateur (Mail));
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('harry.smith@mymail.com', 'a', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('gb@ucluvain.be', 'a', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('jw@uclouvain.be', 'harry.smith@mymail.com', 'En attente');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('adb@uclouvain.be', 'a', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('jw@uclouvain.be', 'a', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('LDV@uclouvain.be', 'a', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('gb@ucluvain.be', 'LDV@uclouvain.be', 'Ami');

-- Table: Reponse_questionnnaire
DROP TABLE IF EXISTS Reponse_questionnnaire;
CREATE TABLE Reponse_questionnnaire (IDquestion INTEGER REFERENCES Question (IDquestion) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL, Format TEXT CHECK (format IN ('txt', 'pic')) NOT NULL, Enonce TEXT NOT NULL, Est_solution BOOLEAN NOT NULL CHECK (Est_solution IN ('true', 'false')));

-- Table: Sondage
DROP TABLE IF EXISTS Sondage;
CREATE TABLE Sondage (IDsondage INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, Nombre_choix INTEGER NOT NULL DEFAULT 2, Mail_auteur TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, Intitule TEXT NOT NULL, FOREIGN KEY (Mail_auteur) REFERENCES Utilisateur (Mail));
INSERT INTO Sondage (IDsondage, Nombre_choix, Mail_auteur, Intitule) VALUES (1, 2, 'a', 'Plus belle capitale');
INSERT INTO Sondage (IDsondage, Nombre_choix, Mail_auteur, Intitule) VALUES (2, 2, 'a', 'Nom de mon prochain fils');

-- Table: Utilisateur
DROP TABLE IF EXISTS Utilisateur;
CREATE TABLE Utilisateur (Mail TEXT NOT NULL UNIQUE PRIMARY KEY, Nom TEXT NOT NULL, Prenom TEXT NOT NULL, Mot_de_passe TEXT NOT NULL, Photo TEXT NOT NULL, "Meilleur ami" TEXT, FOREIGN KEY ("Meilleur ami") REFERENCES Utilisateur (Mail));
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('harry.smith@mymail.com', 'Smith', 'Harry', 'hsmIth123', 'default.jpeg', 'a');
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('a', 'Debraie', 'Arthur', 'a', 'My_foto.jpeg', 'gb@ucluvain.be');
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('LDV@uclouvain.be', 'De Vogeleer', 'Louis', '123', 'default.jpeg', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('adb@uclouvain.be', 'de Biolley', 'Antoine', '321', 'default.jpeg', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('jw@uclouvain.be', 'Weber', 'Jimmy', '596', 'default.jpeg', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('gb@ucluvain.be', 'Bellon', 'Guillaume', '0234', 'default.jpeg', NULL);

INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('Celine.dion@ucluvain.be', 'Dion', 'Celine', '1111', 'default.jpeg', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('Angelina.jolie@ucluvain.be', 'Jolie', 'Angelina', '1111', 'default.jpeg', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('Angelika.Putin@ucluvain.be', 'Putin', 'Angelika', '1111', 'default.jpeg', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('Michele.Obama@ucluvain.be', 'Obama', 'Michele', '1111', 'default.jpeg', NULL);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;

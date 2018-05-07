--
-- File generated with SQLiteStudio v3.1.1 on Fri May 4 09:23:02 2018
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Dilemme
DROP TABLE IF EXISTS Dilemme;
CREATE TABLE [Dilemme] ([IDdilemme] INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, [Auteur] TEXT NOT NULL, [Titre] TEXT NOT NULL, [Format] TEXT NOT NULL DEFAULT 'txt' CHECK (format IN ('txt', 'jpeg', 'png')), [Conseiller] TEXT REFERENCES [Utilisateur] ([Mail]) NOT NULL, FOREIGN KEY ([Auteur]) REFERENCES [Utilisateur] ([Mail]));
INSERT INTO Dilemme (IDdilemme, Auteur, Titre, Format, Conseiller) VALUES (1, 'harry.smith@mymail.com', 'Où vais-je manger ce soir?', 'txt', 'arthur98@gmail.com');
INSERT INTO Dilemme (IDdilemme, Auteur, Titre, Format, Conseiller) VALUES (2, 'gb@ucluvain.be', 'Quel pull est ce que j''achète', 'jpeg', 'LDV@uclouvain.be');

-- Table: Participant_Dilemme
DROP TABLE IF EXISTS Participation_Dilemme;
CREATE TABLE Participant_Dilemme (ID_Participant REFERENCES Utilisateur (Mail) ON UPDATE CASCADE NOT NULL, ID_proposition REFERENCES Proposition_dilemme (ID_Proposition) ON UPDATE CASCADE NOT NULL, Evaluation CHECK (evaluation IN ('j aime', 'je n aime pas')));

-- Table: Participation_questionnaire
DROP TABLE IF EXISTS Participation_questionnaire;
CREATE TABLE Participation_questionnaire (Mail TEXT NOT NULL, IDquestionnaire INTEGER NOT NULL REFERENCES Questionnaire (IDquestionnaire), Score INTEGER, PRIMARY KEY (Mail, IDquestionnaire), FOREIGN KEY (Mail) REFERENCES Utilisateur (Mail));
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score) VALUES ('arthur98@gmail.com', 1, 2);
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score) VALUES ('LDV@uclouvain.be', 1, NULL);
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score) VALUES ('LDV@uclouvain.be', 2, 2);
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score) VALUES ('arthur98@gmail.com', 2, 2);

-- Table: Participation_sondage
DROP TABLE IF EXISTS Participation_sondage;
CREATE TABLE Participation_sondage (Mail_participant TEXT NOT NULL, IDsondage INTEGER NOT NULL REFERENCES Sondage (IDsondage), IDchoix INT REFERENCES Proposition_sondage (IDsondage) ON UPDATE CASCADE, Rang INT, FOREIGN KEY (Mail_participant) REFERENCES Utilisateur (Mail), PRIMARY KEY (IDsondage, Mail_participant));
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('harry.smith@mymail.com', 1, 21, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('adb@uclouvain.be', 1, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('gb@ucluvain.be', 1, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('jw@uclouvain.be', 2, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('gb@ucluvain.be', 2, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('harry.smith@mymail.com', 2, NULL, NULL);

-- Table: Proposition_dilemme
CREATE TABLE Proposition_dilemme (ID_dilemme TEXT NOT NULL REFERENCES Dilemme (IDdilemme), Sujet TEXT NOT NULL, Format NOT NULL CHECK (format IN ('txt', 'jpeg')), ID_Proposition TEXT PRIMARY KEY);

-- Table: Proposition_sondage
DROP TABLE IF EXISTS Proposition_sondage;
CREATE TABLE Proposition_sondage (IDsondage INTEGER NOT NULL REFERENCES Sondage (IDsondage), "Ennoncé_de_la_proposition" TEXT NOT NULL);
INSERT INTO Proposition_sondage (IDsondage, "Ennoncé_de_la_proposition") VALUES (1, 'Madrid');
INSERT INTO Proposition_sondage (IDsondage, "Ennoncé_de_la_proposition") VALUES (1, 'Paris');
INSERT INTO Proposition_sondage (IDsondage, "Ennoncé_de_la_proposition") VALUES (1, 'Londres');
INSERT INTO Proposition_sondage (IDsondage, "Ennoncé_de_la_proposition") VALUES (2, 'Bob');
INSERT INTO Proposition_sondage (IDsondage, "Ennoncé_de_la_proposition") VALUES (2, 'Francis');

-- Table: Question
DROP TABLE IF EXISTS Question;
CREATE TABLE Question (IDquestion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, IDquestionnaire TEXT NOT NULL REFERENCES Questionnaire (IDquestionnaire) ON UPDATE CASCADE MATCH FULL, Enoncé TEXT NOT NULL);
INSERT INTO Question (IDquestion, IDquestionnaire, Enoncé) VALUES (1, '1', 'Quand fut découvert l''Amérique ?');
INSERT INTO Question (IDquestion, IDquestionnaire, Enoncé) VALUES (2, '1', 'Création de la Belgique ?');
INSERT INTO Question (IDquestion, IDquestionnaire, Enoncé) VALUES (3, '2', '1+1=?');
INSERT INTO Question (IDquestion, IDquestionnaire, Enoncé) VALUES (4, '2', '3*6=?');
INSERT INTO Question (IDquestion, IDquestionnaire, Enoncé) VALUES (5, '1', 'Bataille de Hastings');

-- Table: Questionnaire
DROP TABLE IF EXISTS Questionnaire;
CREATE TABLE Questionnaire (IDquestionnaire INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, Titre TEXT NOT NULL, Auteur TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE MATCH SIMPLE);
INSERT INTO Questionnaire (IDquestionnaire, Titre, Auteur) VALUES (1, 'Histoire', 'harry.smith@mymail.com');
INSERT INTO Questionnaire (IDquestionnaire, Titre, Auteur) VALUES (2, 'Maths', 'harry.smith@mymail.com');

-- Table: Relation
DROP TABLE IF EXISTS Relation;
CREATE TABLE Relation (Utilisateur1 TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE MATCH FULL, Utilisateur2 TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE MATCH FULL, Statut TEXT NOT NULL DEFAULT 'En attente' CHECK (statut IN ('Ami', 'En attente', 'Rejet')), PRIMARY KEY (Utilisateur1, Utilisateur2), FOREIGN KEY (Utilisateur2) REFERENCES Utilisateur (Mail), FOREIGN KEY (Utilisateur1) REFERENCES Utilisateur (Mail));
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('harry.smith@mymail.com', 'arthur98@gmail.com', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('gb@ucluvain.be', 'arthur98@gmail.com', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('jw@uclouvain.be', 'harry.smith@mymail.com', 'En attente');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('adb@uclouvain.be', 'arthur98@gmail.com', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('jw@uclouvain.be', 'arthur98@gmail.com', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('LDV@uclouvain.be', 'arthur98@gmail.com', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('gb@ucluvain.be', 'LDV@uclouvain.be', 'Ami');

-- Table: Réponse_questionnnaire
DROP TABLE IF EXISTS Réponse_questionnaire;
CREATE TABLE Réponse_questionnnaire (ID_question REFERENCES Question (IDquestion) ON UPDATE CASCADE NOT NULL, Format CHECK (format IN ('txt', 'img')) NOT NULL, Enoncé NOT NULL, Est_solution BOOLEAN NOT NULL CHECK (Est_solution IN ('true', 'false')));

-- Table: Sondage
DROP TABLE IF EXISTS Sondage;
CREATE TABLE Sondage (IDsondage INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, Nombre_choix INTEGER NOT NULL DEFAULT 2, Mail_auteur TEXT NOT NULL, Intitulé TEXT NOT NULL, FOREIGN KEY (Mail_auteur) REFERENCES Utilisateur (Mail));
INSERT INTO Sondage (IDsondage, Nombre_choix, Mail_auteur, Intitulé) VALUES (1, 2, 'a', 'Plus belle capitale');
INSERT INTO Sondage (IDsondage, Nombre_choix, Mail_auteur, Intitulé) VALUES (2, 2, 'a', 'Nom de mon prochain fils');

-- Table: Utilisateur
DROP TABLE IF EXISTS Utilisateur;
CREATE TABLE Utilisateur (Mail TEXT NOT NULL UNIQUE PRIMARY KEY, Nom TEXT NOT NULL, Prénom TEXT NOT NULL, "Mot de passe" TEXT NOT NULL, Photo NUMERIC DEFAULT 'default.jpeg' NOT NULL, "Meilleur ami" TEXT, FOREIGN KEY ("Meilleur ami") REFERENCES Utilisateur (Mail));
INSERT INTO Utilisateur (Mail, Nom, Prénom, "Mot de passe", Photo, "Meilleur ami") VALUES ('harry.smith@mymail.com', 'Smith', 'Harry', 'hsmIth123', 'default.jpeg', 'arthur98@gmail.com');
INSERT INTO Utilisateur (Mail, Nom, Prénom, "Mot de passe", Photo, "Meilleur ami") VALUES ('arthur98@gmail.com', 'Debraie', 'Arthur', 'mot2passe', 'My_foto.jpeg', 'gb@ucluvain.be');
INSERT INTO Utilisateur (Mail, Nom, Prénom, "Mot de passe", Photo, "Meilleur ami") VALUES ('LDV@uclouvain.be', 'De Vogeleer', 'Louis', 123, 'default.jpeg', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prénom, "Mot de passe", Photo, "Meilleur ami") VALUES ('adb@uclouvain.be', 'de Biolley', 'Antoine', 321, 'default.jpeg', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prénom, "Mot de passe", Photo, "Meilleur ami") VALUES ('jw@uclouvain.be', 'Weber', 'Jimmy', 596, 'default.jpeg''', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prénom, "Mot de passe", Photo, "Meilleur ami") VALUES ('gb@uclouvain.be', 'Bellon', 'Guillaume', '0234', 'default.jpeg', NULL);
INSERT INTO Utilisateur (Mail, Nom, Prénom, "Mot de passe", Photo, "Meilleur ami") VALUES ('a', 'Nom', 'Prénom', 'a', 'default.jpeg', NULL);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;

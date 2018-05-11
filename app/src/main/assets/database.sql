--
-- File generated with SQLiteStudio v3.1.1 on mar. mai 8 12:20:03 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Dilemme
DROP TABLE IF EXISTS Dilemme;
CREATE TABLE Dilemme (IDdilemme INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, Auteur TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, Titre TEXT NOT NULL, Participant TEXT REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL, FOREIGN KEY (Auteur) REFERENCES Utilisateur (Mail));
INSERT INTO Dilemme (IDdilemme, Auteur, Titre, Participant) VALUES (1, 'harry.smith@mymail.com', 'O? vais-je manger ce soir?', 'gb@uclouvain.be');
INSERT INTO Dilemme (IDdilemme, Auteur, Titre, Participant) VALUES (2, 'gb@uclouvain.be', 'Quel pull est ce que j''ach?te', 'harry.smith@mymail.com');

-- Table: Participant_dilemme
DROP TABLE IF EXISTS Participant_dilemme;
CREATE TABLE Participant_dilemme (ID_proposition REFERENCES Proposition_dilemme (IDproposition) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL, Evaluation CHECK (evaluation IN ('j aime', 'je n aime pas')));

-- Table: Proposition_dilemme
DROP TABLE IF EXISTS Proposition_dilemme;
CREATE TABLE Proposition_dilemme (IDdilemme TEXT NOT NULL REFERENCES Dilemme (IDdilemme) ON DELETE CASCADE ON UPDATE CASCADE, Sujet TEXT NOT NULL, Format TEXT NOT NULL CHECK (format IN ('txt', 'pic')), IDproposition TEXT PRIMARY KEY NOT NULL REFERENCES Proposition_dilemme (IDproposition) ON DELETE CASCADE ON UPDATE CASCADE);

-- Table: Participation_questionnaire
DROP TABLE IF EXISTS Participation_questionnaire;
CREATE TABLE Participation_questionnaire (Mail TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, IDquestionnaire INTEGER NOT NULL REFERENCES Questionnaire (IDquestionnaire) ON DELETE CASCADE ON UPDATE CASCADE, Score INTEGER DEFAULT (0), Statut INTEGER DEFAULT (0), PRIMARY KEY (Mail, IDquestionnaire), FOREIGN KEY (Mail) REFERENCES Utilisateur (Mail));
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score, Statut) VALUES ('a', 1, 2, 3);
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score, Statut) VALUES ('ldv@uclouvain.be', 1, 0, 0);
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score, Statut) VALUES ('ldv@uclouvain.be', 2, 1, 2);
INSERT INTO Participation_questionnaire (Mail, IDquestionnaire, Score, Statut) VALUES ('a', 2, 1, 1);

-- Table: Sondage
DROP TABLE IF EXISTS Sondage;
CREATE TABLE Sondage (IDsondage INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, Nombre_choix INTEGER NOT NULL DEFAULT 2, Mail_auteur TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, Intitule TEXT NOT NULL, FOREIGN KEY (Mail_auteur) REFERENCES Utilisateur (Mail));
INSERT INTO Sondage (IDsondage, Nombre_choix, Mail_auteur, Intitule) VALUES (1, 3, 'a', 'Plus belle capitale');
INSERT INTO Sondage (IDsondage, Nombre_choix, Mail_auteur, Intitule) VALUES (2, 2, 'harry.smith@mymail.com', 'Nom de mon prochain fils');
INSERT INTO Sondage (IDsondage, Nombre_choix, Mail_auteur, Intitule) VALUES (3, 2, 'jd@uclouvain.be', 'Vacances entre potes');
INSERT INTO Sondage (IDsondage, Nombre_choix, Mail_auteur, Intitule) VALUES (4, 2, 'jw@uclouvain.be', 'Film cinéma vendredi');
INSERT INTO Sondage (IDsondage, Nombre_choix, Mail_auteur, Intitule) VALUES (5, 2, 'ew@uclouvain.be', 'Participation soirée de gala');

-- Table: Participation_sondage
DROP TABLE IF EXISTS Participation_sondage;
CREATE TABLE Participation_sondage (Mail_participant TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, IDsondage INTEGER NOT NULL REFERENCES Sondage (IDsondage) ON DELETE CASCADE ON UPDATE CASCADE, IDchoix INTEGER REFERENCES Proposition_sondage (IDproposition), Rang INT, FOREIGN KEY (Mail_participant) REFERENCES Utilisateur (Mail), PRIMARY KEY (IDsondage, Mail_participant));
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('harry.smith@mymail.com', 1, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('adb@uclouvain.be', 1, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('gb@uclouvain.be', 1, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('jw@uclouvain.be', 2, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('gb@uclouvain.be', 2, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('ldv@uclouvain.be', 2, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('a', 3, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('gb@uclouvain.be', 3, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('harry.smith@mymail.com', 3, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('tc@uclouvain.be', 3, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('jw@uclouvain.be', 3, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('gb@uclouvain.be', 4, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('harry.smith@mymail.com', 4, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('cd@uclouvain.be', 4, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('a', 4, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('a', 5, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('tc@uclouvain.be', 5, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('gb@uclouvain.be', 5, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('cd@uclouvain.be', 5, NULL, NULL);
INSERT INTO Participation_sondage (Mail_participant, IDsondage, IDchoix, Rang) VALUES ('ldv@uclouvain.be', 5, NULL, NULL);


-- Table: Proposition_sondage
DROP TABLE IF EXISTS Proposition_sondage;
CREATE TABLE Proposition_sondage (IDsondage INTEGER NOT NULL REFERENCES Sondage (IDsondage) ON DELETE CASCADE ON UPDATE CASCADE, Ennonce_de_la_proposition TEXT NOT NULL, IDproposition INTEGER NOT NULL);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (1, 'Madrid',1);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (1, 'Paris',2);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (1, 'Londres',3);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (1, 'Bruxelles',4);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (1, 'Tokio',5);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (1, 'Mexico',6);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (2, 'Bob',7);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (2, 'Francis',8);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (2, 'Henry',9);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (2, 'Enzo',10);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (3, 'Week-end à Knokke',11);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (3, 'Semaine dans les Ardennes',12);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (3, 'City trip à Prague',13);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (4, 'Ready Player One',14);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (4, 'Pierre Lapin',15);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (4, 'Avengers : Infinity War',16);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (4, 'Comme des rois',17);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (4, 'Death wish',18);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (4, 'Gaston Lagaffe',19);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (5, 'Je viens',20);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (5, 'Je ne sais pas encore',21);
INSERT INTO Proposition_sondage (IDsondage, Ennonce_de_la_proposition, IDproposition) VALUES (5, 'Je ne suis pas là',22);

-- Table: Question
DROP TABLE IF EXISTS Question;
CREATE TABLE Question (IDquestion INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, IDquestionnaire TEXT NOT NULL REFERENCES Questionnaire (IDquestionnaire) ON DELETE CASCADE ON UPDATE CASCADE, Enonce TEXT NOT NULL, number INTEGER NOT NULL);
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce, number) VALUES (1, '1', 'Quand fut découvert l''Amérique ?', 1);
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce, number) VALUES (2, '1', 'Création de la Belgique ?', 2);
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce, number) VALUES (3, '1', 'Actuel Roi des belges ?', 3);
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce, number) VALUES (4, '2', '1+1=?', 1);
INSERT INTO Question (IDquestion, IDquestionnaire, Enonce, number) VALUES (5, '2', '3*6=?', 2);

-- Table: Questionnaire
DROP TABLE IF EXISTS Questionnaire;
CREATE TABLE Questionnaire (IDquestionnaire INTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE, Titre TEXT NOT NULL, Auteur TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE);
INSERT INTO Questionnaire (IDquestionnaire, Titre, Auteur) VALUES (1, 'Histoire', 'harry.smith@mymail.com');
INSERT INTO Questionnaire (IDquestionnaire, Titre, Auteur) VALUES (2, 'Maths', 'harry.smith@mymail.com');

-- Table: Relation
DROP TABLE IF EXISTS Relation;
CREATE TABLE Relation (Utilisateur1 TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE MATCH FULL, Utilisateur2 TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, Statut TEXT NOT NULL DEFAULT 'En_attente' CHECK (statut IN ('Ami', 'En_attente', 'Rejet')), PRIMARY KEY (Utilisateur1, Utilisateur2), FOREIGN KEY (Utilisateur2) REFERENCES Utilisateur (Mail), FOREIGN KEY (Utilisateur1) REFERENCES Utilisateur (Mail));
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('harry.smith@mymail.com', 'a', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('gb@uclouvain.be', 'a', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('jw@uclouvain.be', 'harry.smith@mymail.com', 'En_attente');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('adb@uclouvain.be', 'a', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('jw@uclouvain.be', 'a', 'Ami');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('ldv@uclouvain.be', 'a', 'En_attente');
INSERT INTO Relation (Utilisateur1, Utilisateur2, Statut) VALUES ('gb@uclouvain.be', 'ldv@uclouvain.be', 'Ami');

-- Table: Reponse_questionnnaire
DROP TABLE IF EXISTS Reponse_questionnaire;
CREATE TABLE Reponse_questionnaire (IDquestion INTEGER REFERENCES Question (IDquestion) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL, Format TEXT CHECK (format IN ('txt', 'pic')) NOT NULL, Texte TEXT NOT NULL, Est_solution TEXT NOT NULL CHECK (Est_solution IN ('true', 'false')));
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (1, 'txt', '1400', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (1, 'txt', '1300', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (1, 'txt', '1200', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (1, 'txt', '1498', 'true');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (2, 'txt', '1831', 'true');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (2, 'txt', '1800', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (2, 'txt', '1850', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (2, 'txt', 'Wallonie indépendante', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (3, 'txt', 'Albert 1', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (3, 'txt', 'Philippe', 'true');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (3, 'txt', 'Baudouin', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (3, 'txt', 'Albert 2', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (4, 'txt', '1', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (4, 'txt', '2', 'true');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (4, 'txt', '3', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (4, 'txt', '4', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (5, 'txt', '18', 'true');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (5, 'txt', '21', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (5, 'txt', '12', 'false');
INSERT INTO Reponse_questionnaire (IDquestion, Format, Texte, Est_solution) VALUES (5, 'txt', '24', 'false');


-- Table: Utilisateur
DROP TABLE IF EXISTS Utilisateur;
CREATE TABLE Utilisateur (Mail TEXT NOT NULL UNIQUE PRIMARY KEY, Nom TEXT NOT NULL, Prenom TEXT NOT NULL, Mot_de_passe TEXT NOT NULL, Photo BLOB, "Meilleur ami" TEXT, FOREIGN KEY ("Meilleur ami") REFERENCES Utilisateur (Mail));
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('harry.smith@mymail.com', 'Smith', 'Harry', 'hsmIth123', 'default.jpeg', 'a');
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('a', 'a', 'a', 'a',NULL, 'gb@uclouvain.be');
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('ldv@uclouvain.be', 'De Vogeleer', 'Louis', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('adb@uclouvain.be', 'de Biolley', 'Antoine', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('jw@uclouvain.be', 'Weber', 'Jimmy', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('gb@uclouvain.be', 'Bellon', 'Guillaume', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('cd@uclouvain.be', 'Dion', 'Celine', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('aj@uclouvain.be', 'Jolie', 'Angelina', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('mo@uclouvain.be', 'Obama', 'Michele', '1111', NULL, NULL);

INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('jd@uclouvain.be', 'Depp', 'Johnny', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('bp@uclouvain.be', 'Pitt', 'Brad', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('jl@uclouvain.be', 'Lawrence', 'Jennifer', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('tc@uclouvain.be', 'Cruise', 'tom', '1111', NULL, NULL);
INSERT INTO Utilisateur (Mail, Nom, Prenom, Mot_de_passe, Photo, "Meilleur ami") VALUES ('ew@uclouvain.be', 'Watson', 'Emma', '1111', NULL, NULL);



COMMIT TRANSACTION;
PRAGMA foreign_keys = on;

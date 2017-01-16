create database IF NOT EXISTS serwis_informacyjny_to;

use serwis_informacyjny_to;

CREATE TABLE IF NOT EXISTS Uzytkownik ( 
    Id int PRIMARY KEY AUTO_INCREMENT, 
    Imie VARCHAR(25) NOT NULL, 
    Nazwisko VARCHAR(50) NOT NULL, 
    Login VARCHAR(25) NOT NULL, 
    Haslo VARCHAR(50) NOT NULL, 
    Typ_Konta int NOT NULL, 
    Status_Konta int NOT NULL, 
    Data_Utworzenia Date NULL, 
    Data_Zbanowania Date NULL
);

CREATE TABLE IF NOT EXISTS Artykul ( 
    Id int PRIMARY KEY AUTO_INCREMENT,
    Autor VARCHAR(25) NOT NULL,
    Tytul VARCHAR(25) NOT NULL, 
    Tresc VARCHAR(1000) NOT NULL, 
    Status_artykulu int NOT NULL, 
    Kategoria int NOT NULL,
    Obrazek VARCHAR(100) NULL,
    Data_Publikacji Date NULL
);

CREATE TABLE IF NOT EXISTS Komentarze ( 
    Id int PRIMARY KEY AUTO_INCREMENT,
    Tresc VARCHAR(1000) NOT NULL,
    Autor_Login VARCHAR(25) NOT NULL,
    Artykul_Id int NOT NULL,
    Data_Utworzenia Date NULL
);

CREATE TABLE IF NOT EXISTS Reklamy ( 
    Id int PRIMARY KEY AUTO_INCREMENT,
    Data_Dodania Date NULL,
    Dostawca VARCHAR(25) NOT NULL, 
    Link VARCHAR(100) NOT NULL, 
    Koszt int NOT NULL
);



INSERT INTO Uzytkownik(Imie, Nazwisko, Login, Haslo, Typ_Konta, Status_Konta) VALUES('Jan', 'Kowalski', 'janek1', 'janek1', 1, 1);
INSERT INTO Uzytkownik(Imie, Nazwisko, Login, Haslo, Typ_Konta, Status_Konta) VALUES('Artur', 'Nowak', 'artur1', 'artur1', 2, 1);
INSERT INTO Uzytkownik(Imie, Nazwisko, Login, Haslo, Typ_Konta, Status_Konta) VALUES('Redaktor', 'Nowak', 'redaktor1', 'redaktor1', 3, 1);
INSERT INTO Uzytkownik(Imie, Nazwisko, Login, Haslo, Typ_Konta, Status_Konta) VALUES('Admin', 'Nowak', 'admin1', 'admin1', 4, 1);
INSERT INTO Artykul(Autor, Tytul, Tresc, Status_artykulu, Kategoria, Obrazek, Data_Publikacji) VALUES('redaktor2', 'Tytul1', 'Tresc1', 0, 0, 'http://lorempixel.com/250/250/', NOW());
INSERT INTO Komentarze(Tresc, Autor_Login, Artykul_Id, Data_Utworzenia) VALUES('TrescKomentarza1', 'janek1',1, NOW());
INSERT INTO Komentarze(Tresc, Autor_Login, Artykul_Id, Data_Utworzenia) VALUES('TrescKomentarza1', 'artur1',1, NOW());
INSERT INTO reklamy(Data_Dodania, Dostawca, Link, Koszt) VALUES(NOW(), 'IBM', 'http://lorempixel.com/250/250/', 12);
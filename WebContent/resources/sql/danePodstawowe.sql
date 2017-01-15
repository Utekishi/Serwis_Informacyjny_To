create database IF NOT EXISTS serwis_informacyjny_to;

use serwis_informacyjny_to;

CREATE TABLE IF NOT EXISTS Uzytkownik ( 
    Id int PRIMARY KEY AUTO_INCREMENT, 
    Imie VARCHAR(25) NOT NULL, 
    Nazwisko VARCHAR(50) NOT NULL, 
    Login VARCHAR(50) NOT NULL, 
    Haslo VARCHAR(50) NOT NULL, 
    Typ_Konta int NOT NULL, 
    Status_Konta int NOT NULL, 
    Data_Utworzenia Date NULL, 
    Data_Zbanowania Date NULL
);

CREATE TABLE IF NOT EXISTS Artykul ( 
    Id int PRIMARY KEY AUTO_INCREMENT,
    Autor int NOT NULL,
    Tytul VARCHAR(25) NOT NULL, 
    Tresc MEDIUMTEXT NOT NULL, 
    Status_artykulu int NOT NULL, 
    Kategoria int NOT NULL,
    Obrazek VARCHAR(100) NULL,
    Data_Publikacji Date NULL
);

CREATE TABLE IF NOT EXISTS Komentarze ( 
    Id int PRIMARY KEY,
    Tresc MEDIUMTEXT NOT NULL,
    Autor_Id int NOT NULL,
    Artykul_Id int NOT NULL,
    Data_Utworzenia Date NULL,
    CONSTRAINT fk_komentarz_autor
    FOREIGN KEY (Autor_id) REFERENCES Uzytkownik (id),
    CONSTRAINT fk_komentarz_artykul
    FOREIGN KEY (Artykul_Id) REFERENCES Artykul (id)
);




INSERT INTO Uzytkownik(Id, Imie, Nazwisko, Login, Haslo, Typ_Konta, Status_Konta) VALUES(1, 'Jan', 'Kowalski', 'janek1', 'janek1', 1, 1);
INSERT INTO Uzytkownik(Id, Imie, Nazwisko, Login, Haslo, Typ_Konta, Status_Konta) VALUES(2, 'Artur', 'Nowak', 'artur1', 'artur1', 1, 1);
INSERT INTO Artykul(Id, Autor, Tytul, Tresc, Status_artykulu, Kategoria, Obrazek, Data_Publikacji) VALUES(1, 1, 'Tytul1', 'Tresc1', 0, 0, 'http://lorempixel.com/250/250/', NOW());
INSERT INTO Artykul(Id, Autor, Tytul, Tresc, Status_artykulu, Kategoria, Obrazek, Data_Publikacji) VALUES(2, 2, 'Tytul2', 'Tresc2', 0, 0, 'http://lorempixel.com/250/250/', NOW());
INSERT INTO Komentarze(Id,Tresc, Autor_id, Artykul_Id, Data_Utworzenia) VALUES(1,'TrescKomentarza1', 1,1, NOW());
INSERT INTO Komentarze(Id,Tresc, Autor_id, Artykul_Id, Data_Utworzenia) VALUES(2,'TrescKomentarza2', 2,1, NOW());
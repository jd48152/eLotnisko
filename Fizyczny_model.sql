CREATE DATABASE eLotnisko;
CREATE TABLE Kraje			
							(
							Id_kraju int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_kraju varchar(50) NOT NULL
							);
CREATE TABLE Miasta			
							(
							Id_miasta		int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_miasta	varchar(50) NOT NULL,
							Id_kraju		int NOT NULL REFERENCES Kraje (Id_kraju)
							);
CREATE TABLE Linie_lotnicze 
							(
							Id_linii		int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_linii		varchar(50)  NOT NULL
							);
CREATE TABLE Terminale		
							(
							Id_terminala	int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_terminala varchar(50) NOT NULL
							);
CREATE TABLE Tasmociagi		
							(
							Id_tasmociagu	int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_tasmociagu varchar(50) NOT NULL
							);
CREATE TABLE KodyPocztowe	
							(				
							Id_kodu			int PRIMARY KEY AUTO_INCREMENT,
							Kod_pocztowy	varchar(10) NOT NULL
							);
CREATE TABLE MiastaKodyPocztowe
							(
							Id_kodu			int REFERENCES KodyPocztowe (Id_kodu),
							Id_miasta		int REFERENCES Miasta (Id_miasta) ON DELETE CASCADE,
							PRIMARY KEY (Id_kodu,Id_miasta)
							);
CREATE TABLE Lotniska		
							(
							Id_lotniska		int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_lotniska	varchar(100) NOT NULL, 
							Miasto_id		int  NOT NULL REFERENCES Miasta(Id_miasta),
							Ulica			varchar(50),
							Numer			int
							);
CREATE TABLE KlasyLotu
							(
							Id_klasy		int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_klasy		varchar(50) NOT NULL,
							Symbol_klasy	varchar(10) NOT NULL
							);
CREATE TABLE Samoloty		
							(
							Id_samolotu		int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_samolotu	varchar(50) NOT NULL,
							/*Ilosc_miejsc	int			NOT NULL, Informacja nieprzydatna, brak odniesienia do klasy lotu*/
							Id_lini			int			NOT NULL REFERENCES Linie_lotnicze (Id_linii)
							);
CREATE TABLE SamolotyKlasy 
							(
							Id_samolotu int REFERENCES Samoloty (Id_samolotu) ON DELETE CASCADE,
							Id_klasy	int REFERENCES KlasyLotu(Id_klasy) ON DELETE CASCADE,
							PRIMARY KEY (Id_samolotu,Id_klasy)
							);
CREATE TABLE Loty			
							(
							Id_lotu				int PRIMARY KEY AUTO_INCREMENT,
							PrefixRejsu			varchar(5)NOT NULL,
							NumerRejsu			int		 NOT NULL,
							Samolot				int		 NOT NULL	REFERENCES Samoloty(Id_samolotu),
							TerminalStart		int		 NOT NULL	REFERENCES Terminale(Id_terminala),
							TasmociagStart		int		 NOT NULL	REFERENCES Tasmociagi(Id_tasmociagu),
							LotniskoStartowe	int		 NOT NULL	REFERENCES Lotniska(Id_lotniska),
							LotniskoDocelowe	int		 NOT NULL	REFERENCES Lotniska(Id_lotniska),
							CzasOdlotu			datetime NOT NULL,
							CzasPrzylotu		datetime NOT NULL,
							CenaBrutto			decimal (12,4) NOT NULL,
							StatusLotu			tinyint	 NOT NULL
							);
CREATE TABLE MiejscaLoty 
							(
							Id_mLot				int PRIMARY KEY AUTO_INCREMENT ,
							Id_lotu				int NOT NULL REFERENCES Loty(Id_lotu),
							Id_klasy			int NOT NULL REFERENCES KlasyLotu (Id_klasy),
							/*Wolne_miejsca		int NOT NULL Informacja zbędna, wystarczy odejmować od Razem_miejsca ilości z tabeli RezerwacjePasazerowieKlasy*/
							Razem_miejsca		int NOT NULL
							);
CREATE TABLE Pasazerowie	(
							Id_pasazera			int	PRIMARY KEY AUTO_INCREMENT,
							Imie				varchar(50) NOT NULL,
							Nazwisko			varchar(50) NOT NULL,
							Email				varchar(50) NOT NULL,
							NrTelefonu			varchar(20) NOT NULL
							);	
CREATE TABLE Role
							(
							Id_roli				int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_roli			varchar(50) NOT NULL
							);
CREATE TABLE Operatorzy
							(
							Id_operatora		int PRIMARY KEY AUTO_INCREMENT,
							Imie				varchar(50) NOT NULL,
							Nazwisko			varchar(50) NOT NULL,
							email				varchar(50) NOT NULL,
							haslo				varchar (150) NOT NULL,
							Id_roli				int NOT NULL REFERENCES Role (Id_roli)
							);
CREATE TABLE Uprawnienia	(
							Id_uprawnienia		int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_uprawnienia	varchar(50) NOT NULL,
							Opis				varchar(1024)
							);
CREATE TABLE UprawnieniaRole
							(
							Id_uprawnienia		int REFERENCES Uprawnienia (Id_Uprawnienia) ON DELETE CASCADE,
							Id_roli				int REFERENCES Role (Id_roli)	ON DELETE CASCADE,
							PRIMARY KEY (Id_uprawnienia,Id_roli)
							);						
CREATE TABLE Rezerwacje
							(
							Id_rezerwacji		int PRIMARY KEY AUTO_INCREMENT,
							Id_lotu				int  NOT NULL REFERENCES Loty (Id_lotu),
							Rezerwujacy			int  NOT NULL REFERENCES Pasazerowie (Id_pasazera),
							Numer_rezerwacji	int  NOT NULL,
							Data_rezerwacji		date NOT NULL,
							CzyRozliczone		bit  NOT NULL,
							Rozliczyl			int  NOT NULL REFERENCES Operatorzy (Id_operatora),
							Notatka_rezerwującego varchar(100),
							Status_rezerwacji	tinyint
							);
CREATE TABLE Bilety		
						(
							Id_biletu		int PRIMARY KEY AUTO_INCREMENT,
							Numer_biletu	int NOT NULL,
							Data_wystawienia date NOT NULL,
							Id_rezerwacji	int NOT NULL REFERENCES Rezerwacje (Id_rezerwacji) ON DELETE CASCADE
						);

CREATE TABLE KartaBagazowa 
							(
							Id_karty		int PRIMARY KEY AUTO_INCREMENT,
							Numer_karty		int NOT NULL
							);
CREATE TABLE KartaPokladowa
							(
							Id_karty		int PRIMARY KEY AUTO_INCREMENT,
							Nr_miejsca		int NOT NULL,
							Rzad			char NOT NULL
							);
CREATE TABLE  BiletyKartyBagazowe /*Wiele kart bagażowych może być przypisanych do jednego biletu (np. bilet zbiorowy).*/
							(
							Id_biletu		int REFERENCES Bilety (Id_biletu),
							Id_karty		int REFERENCES KartaBagazowa (Id_karty),
							PRIMARY KEY (Id_biletu,Id_karty)
							);
CREATE TABLE  BiletyKartyPokladowe /*Wiele kart pokładowych może być przypisanych do jednego biletu (np. bilet zbiorowy).*/
							(	
							Id_biletu		int REFERENCES Bilety (Id_biletu),
							Id_karty		int REFERENCES KartaPokladowa (Id_karty),
							PRIMARY KEY(Id_biletu,Id_karty)
							);
CREATE TABLE RezerwacjePasazerowieKlasy
							(
							Id_rPasazerowieKlasy int PRIMARY KEY,
							Id_rezerwacji	int REFERENCES Rezerwacje (Id_rezerwacji) ,
							Id_pasazera		int REFERENCES Pasazerowie (Id_pasazera),
							Id_klasy		int REFERENCES KlasyLotu (Id_klasy),
							/*PRIMARY KEY (Id_rezerwacji,Id_pasazera,Id_klasy)*/
							);
CREATE TABLE Waluty			(
							Id_waluty	int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_waluty varchar(50) NOT NULL,
							Skrot_waluty varchar(5) NOT NULL
							);
CREATE TABLE KursyWaluty 
							(
							Id_kursu	int PRIMARY KEY AUTO_INCREMENT,
							Id_waluty	int NOT NULL REFERENCES Waluty (Id_waluty),
							Wartosc_kursu decimal(9,4) NOT NULL,
							DataOd datetime NOT NULL,
							DataDo datetime
							);
CREATE TABLE FormyPlatnosci (
							Id_formy int PRIMARY KEY AUTO_INCREMENT,
							Nazwa_formy varchar(50) NOT NULL
							);
CREATE TABLE RezerwacjePlatnosci
							(
							Id_rPlatnosci int PRIMARY KEY AUTO_INCREMENT,
							Id_rezerwacji int NOT NULL REFERENCES Rezerwacje (Id_rezerwacji),
							Id_formyPlatnosci int NOT NULL REFERENCES FormyPlatnosci (Id_formy),
							Data_platnosci datetime NOT NULL,
							WplaconoBrutto decimal(12,4) NOT NULL,
							Id_waluty int NOT NULL REFERENCES Waluty (Id_waluty),
							Notatka_platnosci varchar(100)
							);
CREATE TABLE StrefaZwrotow_Covid
							(
							Id_sCovid int PRIMARY KEY AUTO_INCREMENT,
							Id_rPasazerowieKlasy int NOT NULL REFERENCES RezerwacjePasazerowieKlasy (Id_rPasazerowieKlasy),
							/*Id_rezerwacji int NOT NULL REFERENCES Rezerwacje (Id_rezerwacji),*/
							Opis_przypadku varchar(200) NOT NULL
							);
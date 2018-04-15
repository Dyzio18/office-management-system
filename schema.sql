-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               10.1.30-MariaDB - mariadb.org binary distribution
-- Serwer OS:                    Win32
-- HeidiSQL Wersja:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Zrzut struktury bazy danych company
DROP DATABASE IF EXISTS `company`;
CREATE DATABASE IF NOT EXISTS `company` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */;
USE `company`;

-- Zrzut struktury tabela company.cases
DROP TABLE IF EXISTS `cases`;
CREATE TABLE IF NOT EXISTS `cases` (
  `idCase` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `notes` text COLLATE utf8_polish_ci,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `state` enum('open','closed') COLLATE utf8_polish_ci DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCase`),
  KEY `idClient` (`idClient`),
  KEY `idUser` (`idUser`),
  CONSTRAINT `FK_cases_clients` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClient`),
  CONSTRAINT `FK_cases_users` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Data exporting was unselected.
-- Zrzut struktury tabela company.clients
DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `surname` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Data exporting was unselected.
-- Zrzut struktury tabela company.expenditures
DROP TABLE IF EXISTS `expenditures`;
CREATE TABLE IF NOT EXISTS `expenditures` (
  `idExpenditure` int(11) NOT NULL AUTO_INCREMENT,
  `idCase` int(11) DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `price` decimal(10,2) unsigned DEFAULT NULL,
  `date` date DEFAULT NULL,
  `amount` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`idExpenditure`),
  KEY `idCase` (`idCase`),
  CONSTRAINT `FK_expenditures_cases` FOREIGN KEY (`idCase`) REFERENCES `cases` (`idCase`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Data exporting was unselected.
-- Zrzut struktury tabela company.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `idRole` int(11) NOT NULL AUTO_INCREMENT,
  `nameRole` varchar(50) COLLATE utf8_polish_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Data exporting was unselected.
-- Zrzut struktury tabela company.userdata
DROP TABLE IF EXISTS `userdata`;
CREATE TABLE IF NOT EXISTS `userdata` (
  `idUser` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `surname` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `street` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `phoneNumber` char(13) COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  CONSTRAINT `FK_userdata_users` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Data exporting was unselected.
-- Zrzut struktury tabela company.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `password` char(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `salt` char(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `idRole` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  KEY `idRole` (`idRole`),
  CONSTRAINT `FK_users_roles` FOREIGN KEY (`idRole`) REFERENCES `roles` (`idRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

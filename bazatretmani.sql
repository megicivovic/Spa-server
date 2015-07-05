/*
SQLyog Community v12.09 (64 bit)
MySQL - 5.6.17 : Database - bazatretmani
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bazatretmani` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `bazatretmani`;

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `klijentID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `imePrezime` varchar(50) NOT NULL,
  `korisnickoIme` varchar(50) NOT NULL,
  `korisnickaSifra` varchar(50) NOT NULL,
  PRIMARY KEY (`klijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `klijent` */

insert  into `klijent`(`klijentID`,`imePrezime`,`korisnickoIme`,`korisnickaSifra`) values (1,'Magdalina Civovic','megi','megi'),(2,'Admin','admin','admin'),(3,'Marija Civovic','marijacivovic','marijacivovic'),(4,'Mina Cirkovic','miki','miki'),(5,'Mina Cirkovic','mina','mina'),(6,'Tatjana4','tanja','tanja'),(10,'Luna Lunic','luna','luna'),(11,'Nina Nikolic','niki','niki'),(12,'Marija Civovic','seka','seka'),(13,'Seka Civovic','zuki','seka');

/*Table structure for table `kompanija` */

DROP TABLE IF EXISTS `kompanija`;

CREATE TABLE `kompanija` (
  `kompanijaID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pib` varchar(8) NOT NULL,
  `maticniBroj` varchar(9) NOT NULL,
  `naziv` varchar(50) NOT NULL,
  `ziroRacun` varchar(30) NOT NULL,
  `datumOsnivanja` date NOT NULL,
  `adresa` varchar(50) NOT NULL,
  PRIMARY KEY (`kompanijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `kompanija` */

insert  into `kompanija`(`kompanijaID`,`pib`,`maticniBroj`,`naziv`,`ziroRacun`,`datumOsnivanja`,`adresa`) values (1,'18466','15615613','Loreal','165161515','2015-04-15','Kraljevackog bataljona 162, 3400 Kragujevac'),(2,'265489','64611555','Farcom','9841655613','2015-04-12','27. marta 48, 11000 Beograd'),(9,'3','3','3','3','1992-08-03','3'),(10,'18566232','895462788','Revlon','175-5411-565','1997-02-17','Omaldinskih brigada 67');

/*Table structure for table `preparat` */

DROP TABLE IF EXISTS `preparat`;

CREATE TABLE `preparat` (
  `preparatID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `cena` double unsigned NOT NULL,
  `proizvodjac` int(11) unsigned NOT NULL,
  PRIMARY KEY (`preparatID`,`proizvodjac`),
  KEY `proizvodjac` (`proizvodjac`),
  CONSTRAINT `preparat_ibfk_1` FOREIGN KEY (`proizvodjac`) REFERENCES `kompanija` (`kompanijaID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `preparat` */

insert  into `preparat`(`preparatID`,`naziv`,`cena`,`proizvodjac`) values (1,'ulje za masazu',756,1),(2,'maska za kosu',1890,2),(4,'piling za telo',1750,1),(5,'dermal krem',450,1),(6,'maska za kosu',369,2),(7,'lak za nokte',169,1),(8,'ulje za zanoktice',823,2),(9,'ampula vitamina C',44,2),(10,'fdfds',0,1);

/*Table structure for table `raspored` */

DROP TABLE IF EXISTS `raspored`;

CREATE TABLE `raspored` (
  `zaposleniID` int(10) unsigned NOT NULL,
  `tretmanID` int(10) unsigned NOT NULL,
  `brojTermina` int(11) DEFAULT NULL,
  PRIMARY KEY (`zaposleniID`,`tretmanID`),
  KEY `raspored_ibfk_2` (`tretmanID`),
  CONSTRAINT `raspored_ibfk_2` FOREIGN KEY (`tretmanID`) REFERENCES `tretman` (`tretmanID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `raspored_ibfk_1` FOREIGN KEY (`zaposleniID`) REFERENCES `zaposleni` (`zaposleniID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `raspored` */

insert  into `raspored`(`zaposleniID`,`tretmanID`,`brojTermina`) values (1,5,5),(2,8,2),(3,8,2);

/*Table structure for table `rezervacija` */

DROP TABLE IF EXISTS `rezervacija`;

CREATE TABLE `rezervacija` (
  `rezervacijaID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `zaposleniID` int(11) unsigned NOT NULL,
  `tretmanID` int(11) unsigned zerofill NOT NULL,
  `klijentID` int(11) unsigned zerofill NOT NULL,
  `vreme` datetime DEFAULT NULL,
  PRIMARY KEY (`rezervacijaID`,`zaposleniID`,`tretmanID`,`klijentID`),
  KEY `tretmanID` (`tretmanID`),
  KEY `klijentID` (`klijentID`),
  KEY `rezervacija_ibfk_1` (`zaposleniID`,`tretmanID`),
  CONSTRAINT `rezervacija_ibfk_1` FOREIGN KEY (`zaposleniID`, `tretmanID`) REFERENCES `raspored` (`zaposleniID`, `tretmanID`) ON UPDATE CASCADE,
  CONSTRAINT `rezervacija_ibfk_2` FOREIGN KEY (`tretmanID`) REFERENCES `tretman` (`tretmanID`) ON UPDATE CASCADE,
  CONSTRAINT `rezervacija_ibfk_3` FOREIGN KEY (`klijentID`) REFERENCES `klijent` (`klijentID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;

/*Data for the table `rezervacija` */

insert  into `rezervacija`(`rezervacijaID`,`zaposleniID`,`tretmanID`,`klijentID`,`vreme`) values (27,1,00000000005,00000000001,'2015-07-01 16:00:00'),(29,2,00000000008,00000000001,'2015-07-03 12:40:00'),(30,3,00000000008,00000000001,'2015-07-16 13:00:00'),(46,1,00000000005,00000000001,'2015-07-01 13:59:00'),(47,3,00000000008,00000000001,'2015-07-16 16:00:00');

/*Table structure for table `tretman` */

DROP TABLE IF EXISTS `tretman`;

CREATE TABLE `tretman` (
  `tretmanID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `opis` varchar(70) NOT NULL,
  `cena` double unsigned NOT NULL,
  `trajanjeUMin` int(11) NOT NULL,
  PRIMARY KEY (`tretmanID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `tretman` */

insert  into `tretman`(`tretmanID`,`opis`,`cena`,`trajanjeUMin`) values (5,'tretman lica',756,120),(8,'mikrodermalna abrazija',2506,160),(14,'higijenski tretman lica',2200,150);

/*Table structure for table `tretmanpreparati` */

DROP TABLE IF EXISTS `tretmanpreparati`;

CREATE TABLE `tretmanpreparati` (
  `tretmanID` int(10) unsigned NOT NULL,
  `preparatID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`tretmanID`,`preparatID`),
  KEY `preparatID` (`preparatID`),
  CONSTRAINT `tretmanpreparati_ibfk_1` FOREIGN KEY (`tretmanID`) REFERENCES `tretman` (`tretmanID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tretmanpreparati_ibfk_2` FOREIGN KEY (`preparatID`) REFERENCES `preparat` (`preparatID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tretmanpreparati` */

insert  into `tretmanpreparati`(`tretmanID`,`preparatID`) values (8,1),(8,4),(14,4),(5,5),(5,6);

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `zaposleniID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `imePrezime` varchar(50) DEFAULT NULL,
  `stepenSS` varchar(2) DEFAULT NULL,
  `datumRodjenja` datetime DEFAULT NULL,
  PRIMARY KEY (`zaposleniID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`zaposleniID`,`imePrezime`,`stepenSS`,`datumRodjenja`) values (1,'Marija Markovic','I','1995-04-23 22:37:43'),(2,'Milica Petrovic','I','1992-08-03 00:00:00'),(3,'Jovana Milosevic','II','1985-06-11 20:01:00'),(4,'Svetlana Ruži?','I','1992-08-03 00:00:00'),(5,'Nela Mirkovic','I','1990-08-19 00:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

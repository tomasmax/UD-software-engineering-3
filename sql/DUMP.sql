-- MySQL dump 10.11
--
-- Host: localhost    Database: hibejcc
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `hibejcc`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `hibejcc` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hibejcc`;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `departamento` (
  `DEP_ID` int(11) NOT NULL auto_increment,
  `DEP_NOMBRE` varchar(255) NOT NULL,
  PRIMARY KEY  (`DEP_ID`),
  UNIQUE KEY `DEP_NOMBRE` (`DEP_NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (3,'Comercial'),(1,'Ingenieria del Software'),(2,'Servicios - ESIDE');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `direccion` (
  `DIR_ID` int(11) NOT NULL auto_increment,
  `DIR_DESC` varchar(255) NOT NULL,
  `DIR_CP` int(11) NOT NULL,
  PRIMARY KEY  (`DIR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (1,'Calle1 - Bilbao',48007),(2,'Calle2 - Portugalete',48920);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domicilios`
--

DROP TABLE IF EXISTS `domicilios`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `domicilios` (
  `EM_NIF` int(11) NOT NULL,
  `DIR_ID` int(11) NOT NULL,
  PRIMARY KEY  (`EM_NIF`,`DIR_ID`),
  KEY `FK6F7EBFAEED41DD48` (`DIR_ID`),
  KEY `FK6F7EBFAE26BD5B54` (`EM_NIF`),
  CONSTRAINT `FK6F7EBFAE26BD5B54` FOREIGN KEY (`EM_NIF`) REFERENCES `empleado` (`EM_NIF`),
  CONSTRAINT `FK6F7EBFAEED41DD48` FOREIGN KEY (`DIR_ID`) REFERENCES `direccion` (`DIR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `domicilios`
--

LOCK TABLES `domicilios` WRITE;
/*!40000 ALTER TABLE `domicilios` DISABLE KEYS */;
INSERT INTO `domicilios` VALUES (111,1),(111,2);
/*!40000 ALTER TABLE `domicilios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `empleado` (
  `EM_NIF` int(11) NOT NULL,
  `EM_NOMBRE` varchar(255) NOT NULL,
  `EM_PUESTO` varchar(255) default NULL,
  `EM_EMAIL` varchar(255) default NULL,
  `DEP_ID` int(11) NOT NULL,
  PRIMARY KEY  (`EM_NIF`),
  UNIQUE KEY `EM_NOMBRE` (`EM_NOMBRE`),
  KEY `FK75C3F0CBD5EE2164` (`DEP_ID`),
  CONSTRAINT `FK75C3F0CBD5EE2164` FOREIGN KEY (`DEP_ID`) REFERENCES `departamento` (`DEP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (111,'Asier Perallos Ruiz','profesor','perallos@eside.deusto.es',1),(333,'Maite Sanchez','secretaria','msanchez@eside.deusto.es',2),(444,'Ana Carrera','secretaria','acarrera@eside.deusto.es',2),(555,'Externo1','profesor','ext1@unicomer.es',3);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `externo`
--

DROP TABLE IF EXISTS `externo`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `externo` (
  `EM_NIF` int(11) NOT NULL,
  `EX_EMPRESA` varchar(255) default NULL,
  `EX_EMAILALT` varchar(255) default NULL,
  `EX_DESCCOLABORACION` varchar(255) default NULL,
  PRIMARY KEY  (`EM_NIF`),
  KEY `FKDCF8C0AF26BD5B54` (`EM_NIF`),
  CONSTRAINT `FKDCF8C0AF26BD5B54` FOREIGN KEY (`EM_NIF`) REFERENCES `empleado` (`EM_NIF`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `externo`
--

LOCK TABLES `externo` WRITE;
/*!40000 ALTER TABLE `externo` DISABLE KEYS */;
INSERT INTO `externo` VALUES (555,'Iberdrola','ext1@iberdrola.es','bla, bla,...');
/*!40000 ALTER TABLE `externo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-10-15 11:00:03

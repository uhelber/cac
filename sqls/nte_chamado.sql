CREATE DATABASE  IF NOT EXISTS `nte` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `nte`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: nte
-- ------------------------------------------------------
-- Server version	5.5.28-log

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
-- Table structure for table `chamado`
--

DROP TABLE IF EXISTS `chamado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chamado` (
  `idchamado` int(11) NOT NULL AUTO_INCREMENT,
  `cidade` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `escola` varchar(45) DEFAULT NULL,
  `contato` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `descricao` text,
  `abertopor` int(11) DEFAULT NULL,
  `dataabertura` datetime DEFAULT NULL,
  PRIMARY KEY (`idchamado`),
  KEY `ks_chamado_usuario_idx` (`abertopor`),
  KEY `ks_chamdo_status_idx` (`status`),
  CONSTRAINT `ks_chamado_usuario` FOREIGN KEY (`abertopor`) REFERENCES `usuarios` (`idusuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ks_chamdo_status` FOREIGN KEY (`status`) REFERENCES `status` (`idstatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamado`
--

LOCK TABLES `chamado` WRITE;
/*!40000 ALTER TABLE `chamado` DISABLE KEYS */;
INSERT INTO `chamado` VALUES (1,'João Pessoa','Torre','Raul Córdula',NULL,NULL,1,NULL,2,'2012-10-20 00:00:00'),(2,'Mamanguape','Não identificado','Ruy Carneiro','Jessé',NULL,2,NULL,3,'2012-09-15 00:00:00'),(3,'Itabaiana','Não informado','Prof Marciel','Luciano',NULL,3,NULL,3,'2012-11-27 00:00:00'),(4,'João Pessoa','Centro','Lyceu Paraibano','Ainda não sei...','3218-4355',4,'Configurar roteador...',2,'2012-11-30 14:08:06'),(5,'João Pessoa','Cruz das Armas','Papa Paulo VI','Elvira','1234-4567',4,'Analisar laboratorio...',2,'2012-11-30 16:01:59'),(6,'','','','','',4,'',2,'2012-11-30 17:33:43'),(7,'João Pessoa','Cristo','Liliosa Paiva Leite','Marinho','1234-5678',4,NULL,2,'2012-12-06 12:04:22'),(8,'','','','','',4,'',2,'2012-12-06 12:09:19'),(9,NULL,'Torre','Antônia Rangel','Ainda não sei...','123456',4,NULL,3,'2012-12-06 15:51:05'),(10,NULL,'João Agripino','Capitulina Sátiro','Elvira','123456',4,NULL,4,'2012-12-06 15:57:17');
/*!40000 ALTER TABLE `chamado` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-12-06 17:18:38

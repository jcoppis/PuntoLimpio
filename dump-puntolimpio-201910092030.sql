-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: puntolimpio
-- ------------------------------------------------------
-- Server version	5.7.27

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
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `volumen` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES (1,'coca-cola chica','plastico',273),(2,'coca-cola chica','plastico',273),(3,'coca-cola mediana','plastico',1500),(4,'coca-cola l','plastico',2250),(5,'coca-cola xl','plastico',3000),(6,'caja chica','carton',500),(7,'caja mediana','carton',1500),(8,'caja grande','carton',3500),(9,'vaso chico','vidrio',250),(10,'vaso mediano','vidrio',500),(11,'vaso grande','vidrio',1000);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Itinerario`
--

DROP TABLE IF EXISTS `Itinerario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Itinerario` (
  `id` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `idCamion` int(11) NOT NULL,
  `puntoRecoleccion_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9gcam1o3yb4ri4tv9xxvfxgdj` (`puntoRecoleccion_id`),
  CONSTRAINT `FK9gcam1o3yb4ri4tv9xxvfxgdj` FOREIGN KEY (`puntoRecoleccion_id`) REFERENCES `PuntoRecoleccion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Itinerario`
--

LOCK TABLES `Itinerario` WRITE;
/*!40000 ALTER TABLE `Itinerario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Itinerario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LugarReciclaje`
--

DROP TABLE IF EXISTS `LugarReciclaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LugarReciclaje` (
  `id` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LugarReciclaje`
--

LOCK TABLES `LugarReciclaje` WRITE;
/*!40000 ALTER TABLE `LugarReciclaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `LugarReciclaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LugarReciclaje_Item`
--

DROP TABLE IF EXISTS `LugarReciclaje_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LugarReciclaje_Item` (
  `lugaresDeReciclaje_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  KEY `FK3vv6uaowrghcqbiv2e7h5cbbw` (`items_id`),
  KEY `FKeabqfvr1dgb7drb0bwl1ksp6x` (`lugaresDeReciclaje_id`),
  CONSTRAINT `FK3vv6uaowrghcqbiv2e7h5cbbw` FOREIGN KEY (`items_id`) REFERENCES `Item` (`id`),
  CONSTRAINT `FKeabqfvr1dgb7drb0bwl1ksp6x` FOREIGN KEY (`lugaresDeReciclaje_id`) REFERENCES `LugarReciclaje` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LugarReciclaje_Item`
--

LOCK TABLES `LugarReciclaje_Item` WRITE;
/*!40000 ALTER TABLE `LugarReciclaje_Item` DISABLE KEYS */;
/*!40000 ALTER TABLE `LugarReciclaje_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PuntoRecoleccion`
--

DROP TABLE IF EXISTS `PuntoRecoleccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PuntoRecoleccion` (
  `id` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PuntoRecoleccion`
--

LOCK TABLES `PuntoRecoleccion` WRITE;
/*!40000 ALTER TABLE `PuntoRecoleccion` DISABLE KEYS */;
INSERT INTO `PuntoRecoleccion` VALUES (1,-37.364101,-59.117517),(2,-37.355641,-59.14464),(3,-37.345407,-59.159575),(4,-37.321138,-59.164166),(5,-37.31165,-59.138246),(6,-37.311035,-59.156785),(7,-37.311035,-59.156785),(8,-37.295947,-59.171033),(9,-37.294445,-59.160218),(10,-37.293762,-59.131293),(11,-37.307485,-59.115844);
/*!40000 ALTER TABLE `PuntoRecoleccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `id` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,38.4161,63.6167,'Javier'),(2,-37.32048,-59.132904,'Nelson'),(3,-37.979858,-57.589794,'Juan Manuel'),(4,25.978889,-80.282501,'Guadalupe'),(5,-37.322926,-59.081018,'Maria'),(6,-37.33504,-59.100931,'Federico');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (7),(7),(7),(7),(7),(7);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_item`
--

DROP TABLE IF EXISTS `user_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_item` (
  `id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha_reciclaje` datetime DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `puntoRecoleccion_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoee0d081hqcryyoe8s4rjpop0` (`item_id`),
  KEY `FKs9o5qvau6oxo62u1mm7uqwams` (`puntoRecoleccion_id`),
  KEY `FKp3rfu2xr1mu9rjtf83p4jlxro` (`usuario_id`),
  CONSTRAINT `FKoee0d081hqcryyoe8s4rjpop0` FOREIGN KEY (`item_id`) REFERENCES `Item` (`id`),
  CONSTRAINT `FKp3rfu2xr1mu9rjtf83p4jlxro` FOREIGN KEY (`usuario_id`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FKs9o5qvau6oxo62u1mm7uqwams` FOREIGN KEY (`puntoRecoleccion_id`) REFERENCES `PuntoRecoleccion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_item`
--

LOCK TABLES `user_item` WRITE;
/*!40000 ALTER TABLE `user_item` DISABLE KEYS */;
INSERT INTO `user_item` VALUES (1,2,'2019-09-30 00:00:00',1,1,1),(2,2,'2019-09-30 00:00:00',1,2,2),(3,2,'2019-09-10 00:00:00',1,3,3),(4,2,'2019-09-20 00:00:00',2,4,4),(5,2,'2019-09-30 00:00:00',2,1,1),(6,4,'2019-09-14 00:00:00',2,2,2),(7,4,'2019-09-30 00:00:00',3,3,3),(8,4,'2019-09-15 00:00:00',4,4,4),(9,3,'2019-09-28 00:00:00',5,1,1),(10,5,'2019-09-29 00:00:00',6,2,2),(11,3,'2019-09-03 00:00:00',7,3,3);
/*!40000 ALTER TABLE `user_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'puntolimpio'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-09 20:30:19

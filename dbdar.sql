-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: dbdar
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bookID` int NOT NULL AUTO_INCREMENT,
  `bookName` varchar(100) DEFAULT NULL,
  `bookAutor` varchar(100) DEFAULT NULL,
  `bookType` varchar(100) DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `datePublish` varchar(50) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `quantityDisp` int DEFAULT NULL,
  PRIMARY KEY (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Cien años de soledad','Gabriel García Márquez','Realismo mágico','Editorial Sudamericana','1967',24.50,25),(2,'El alquimista','Paulo Coelho','Ficción','HarperCollins','1988-01-01',16.99,50),(3,'1984','George Orwell','Distopía','Secker & Warburg','1949-06-08',15.99,100),(4,'El principito','Antoine de Saint-Exupéry','Fábula','Reynal & Hitchcock','1943-04-06',12.50,200),(5,'Matar a un ruiseñor','Harper Lee','Ficción','J.B. Lippincott & Co.','1960-07-11',18.75,75),(6,'Orgullo y prejuicio','Jane Austen','Romántico','T. Egerton','1813-01-28',14.20,150),(7,'Don Quijote de la Mancha','Miguel de Cervantes','Novela','Francisco de Robles','1605-01-16',22.50,120),(8,'El gran Gatsby','F. Scott Fitzgerald','Tragedia','Charles Scribner\'s Sons','1925-04-10',17.99,60),(9,'Cumbres borrascosas','Emily Brontë','Drama','Thomas Cautley Newby','1847-12-01',16.40,80),(10,'La sombra del viento','Carlos Ruiz Zafón','Misterio','Planeta','2001-01-01',21.95,95);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookdetail`
--

DROP TABLE IF EXISTS `bookdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookdetail` (
  `bookdetailID` int NOT NULL AUTO_INCREMENT,
  `fkcustomerID` int DEFAULT NULL,
  `quantityChoose` int DEFAULT NULL,
  `fkstateID` int DEFAULT NULL,
  `dateChoose` date DEFAULT NULL,
  `fkbookID` int DEFAULT NULL,
  PRIMARY KEY (`bookdetailID`),
  KEY `fkcustomerID` (`fkcustomerID`),
  KEY `fkstateID_idx` (`fkstateID`),
  KEY `fkbookID_idx` (`fkbookID`),
  CONSTRAINT `bookdetail_ibfk_1` FOREIGN KEY (`fkcustomerID`) REFERENCES `customer` (`customerID`),
  CONSTRAINT `fkbookID` FOREIGN KEY (`fkbookID`) REFERENCES `book` (`bookID`),
  CONSTRAINT `fkstateID` FOREIGN KEY (`fkstateID`) REFERENCES `state` (`stateID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookdetail`
--

LOCK TABLES `bookdetail` WRITE;
/*!40000 ALTER TABLE `bookdetail` DISABLE KEYS */;
INSERT INTO `bookdetail` VALUES (1,1,4,1,'2024-11-25',1),(2,1,7,1,'2024-11-25',1),(3,3,5,2,'2024-11-26',1),(4,1,0,1,'2024-11-28',1);
/*!40000 ALTER TABLE `bookdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `customerName` varchar(100) DEFAULT NULL,
  `LastNameMom` varchar(100) DEFAULT NULL,
  `LastNameDad` varchar(100) DEFAULT NULL,
  `dateBorn` date DEFAULT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Ana','Paula','xd','1998-11-13'),(3,'Darwin','Muruzumbay','Zambrano','2000-11-09'),(4,'Darwin','Muruzumbay','Zambrano','2000-11-11'),(6,'Darwin','Muruzumbay','Zambrano','2024-11-30'),(7,'Darwin','Muruzumbay','Zambrano','2000-06-12'),(8,'Jose','Sanchez','Sarmiento','2001-02-02'),(9,'Jose','Sanchez','Sarmiento','2001-02-02'),(10,'Darwin','Muruzumbay','Zambrano','2000-06-12'),(11,'Jose','Sanchez','Sarmiento','2001-02-02'),(12,'Darwin','Muruzumbay','Zambrano','2000-06-12'),(13,'Jose','Sanchez','Sarmiento','2001-02-02');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail`
--

DROP TABLE IF EXISTS `detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail` (
  `detailID` int NOT NULL AUTO_INCREMENT,
  `fkreceipt` int DEFAULT NULL,
  `fkproduct` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `priceSale` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`detailID`),
  KEY `fkreceipt` (`fkreceipt`),
  KEY `fkproduct` (`fkproduct`),
  CONSTRAINT `detail_ibfk_1` FOREIGN KEY (`fkreceipt`) REFERENCES `receipt` (`receiptID`),
  CONSTRAINT `detail_ibfk_2` FOREIGN KEY (`fkproduct`) REFERENCES `product` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail`
--

LOCK TABLES `detail` WRITE;
/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
INSERT INTO `detail` VALUES (1,5,3,5,0.15),(2,5,2,4,1.85);
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `productName` varchar(100) DEFAULT NULL,
  `productPrice` decimal(5,2) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'apple',1.85,40),(3,'banana',0.15,50);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `receiptID` int NOT NULL AUTO_INCREMENT,
  `receiptDate` date DEFAULT NULL,
  `fkcustomerID` int DEFAULT NULL,
  PRIMARY KEY (`receiptID`),
  KEY `fkcustomerID` (`fkcustomerID`),
  CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`fkcustomerID`) REFERENCES `customer` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (4,'2024-11-23',1),(5,'2024-11-24',1),(6,'2024-11-30',1),(7,'2024-11-30',1),(8,'2024-11-30',6);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `state` (
  `stateID` int NOT NULL AUTO_INCREMENT,
  `stateName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stateID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'Sold'),(2,'Borrowed');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-30 22:35:04

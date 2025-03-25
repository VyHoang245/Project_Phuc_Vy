-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: vpweb
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `date_of_arrival` datetime(6) DEFAULT NULL,
  `description` text,
  `image_link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity_in_stock` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Gibson','Guitar',NULL,'This guitar is a faithful recreation of the classic 1960s J-45, \nfeaturing a round-shoulder dreadnought body with a Sitka spruce top and mahogany back and sides. \nIt has a nitrocellulose finish, a mahogany neck, and a rosewood fretboard. The vintage appointments include Grover tuners, \na white pickguard, and double antiqued binding.','gibson_Acoustic_ACCJ5F910_Ebony_OCRS4560EBN_front.jpg','Gibson 60s J-45 Original Ebony',2999,10),(2,'Gibson','Guitar',NULL,'The Gibson Southern Jumbo Original Vintage Sunburst features a vintage sunburst finish, \na solid spruce top, mahogany back and sides, and a rosewood fretboard. \nKnown for its warm, full sound, it also includes vintage-style tuners and a traditional firestripe pickguard.','gibson_Acoustic_ACCL5X202_Vintage_Sunburst_OCSBL0VS.front.jpg','Gibson Southern Jumbo Original Vintage Sunburst',3499,10),(5,'Gibson',' Guitar',NULL,'The Gibson L-00 Original Vintage Sunburst features a vintage sunburst finish, solid Sitka spruce top, mahogany back and sides, and a rosewood fretboard. Known for its crisp, articulate sound, it offers exceptional clarity and projection, making it ideal for fingerstyle and blues players.','gibson_Acoustic_ACCYBI462_Vintage_Sunburst_OCRSSJVS_front.jpg','Gibson L-00 Original Vintage Sunburst',2799,20);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-04 16:17:23

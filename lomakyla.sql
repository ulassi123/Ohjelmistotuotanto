-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lomakyla
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `asiakas`
--

DROP TABLE IF EXISTS `asiakas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiakas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sahkoposti` varchar(255) NOT NULL,
  `nimi` varchar(255) NOT NULL,
  `puhelinnumero` varchar(255) NOT NULL,
  `maa` varchar(255) NOT NULL,
  `yritys` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `puhelinnumero_UNIQUE` (`puhelinnumero`),
  UNIQUE KEY `sahkoposti_UNIQUE` (`sahkoposti`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asiakas`
--

LOCK TABLES `asiakas` WRITE;
/*!40000 ALTER TABLE `asiakas` DISABLE KEYS */;
INSERT INTO `asiakas` VALUES (1,'henrik.lahti1@example.com','Henrik Lahti','+358400000001','Suomi',0),(2,'emma.virtanen2@example.com','Emma Virtanen','+358400000002','Suomi',0),(3,'janne.korhonen3@example.com','Janne Korhonen','+358400000003','Suomi',0),(4,'tiina.lehtonen4@example.com','Tiina Lehtonen','+358400000004','Suomi',0),(5,'mikko.saarinen5@example.com','Mikko Saarinen','+358400000005','Suomi',0),(6,'linda.heikkinen6@example.com','Linda Heikkinen','+358400000006','Suomi',0),(7,'jari.koskinen7@example.com','Jari Koskinen','+358400000007','Suomi',0),(8,'sanna.niemi8@example.com','Sanna Niemi','+358400000008','Suomi',0),(9,'ville.hakala9@example.com','Ville Hakala','+358400000009','Suomi',0),(10,'elina.savolainen10@example.com','Elina Savolainen','+358400000010','Suomi',0),(11,'juho.makela11@example.com','Juho Mäkelä','+358400000011','Suomi',0),(12,'paula.rantanen12@example.com','Paula Rantanen','+358400000012','Suomi',0),(13,'ari.lepisto13@example.com','Ari Lepistö','+358400000013','Suomi',0),(14,'heidi.kettunen14@example.com','Heidi Kettunen','+358400000014','Suomi',0),(15,'antti.halme15@example.com','Antti Halme','+358400000015','Suomi',0),(16,'sofia.laaksonen16@example.com','Sofia Laaksonen','+358400000016','Suomi',0),(17,'lauri.vainio17@example.com','Lauri Vainio','+358400000017','Suomi',0),(18,'jenni.kuusela18@example.com','Jenni Kuusela','+358400000018','Suomi',0),(19,'marko.siltanen19@example.com','Marko Siltanen','+358400000019','Suomi',0),(20,'optidata.oy@example.com','Optidata Oy','+358400000020','Suomi',1),(21,'laura.kallio21@example.com','Laura Kallio','+358400000021','Suomi',0),(22,'matti.hiltunen22@example.com','Matti Hiltunen','+358400000022','Suomi',0),(23,'riikka.salmi23@example.com','Riikka Salmi','+358400000023','Suomi',0),(24,'timo.nurmi24@example.com','Timo Nurmi','+358400000024','Suomi',0),(25,'sari.koskela25@example.com','Sari Koskela','+358400000025','Suomi',0),(26,'olli.peltonen26@example.com','Olli Peltonen','+358400000026','Suomi',0),(27,'helena.vuori27@example.com','Helena Vuori','+358400000027','Suomi',0),(28,'jussi.aho28@example.com','Jussi Aho','+358400000028','Suomi',0),(29,'minna.leskinen29@example.com','Minna Leskinen','+358400000029','Suomi',0),(30,'pasi.heinonen30@example.com','Pasi Heinonen','+358400000030','Suomi',0),(31,'anu.hakala31@example.com','Anu Hakala','+358400000031','Suomi',0),(32,'sampo.rinne32@example.com','Sampo Rinne','+358400000032','Suomi',0),(33,'hanna.lehto33@example.com','Hanna Lehto','+358400000033','Suomi',0),(34,'pekka.haapala34@example.com','Pekka Haapala','+358400000034','Suomi',0),(35,'maria.koivisto35@example.com','Maria Koivisto','+358400000035','Suomi',0),(36,'eeva.lammi36@example.com','Eeva Lammi','+358400000036','Suomi',0),(37,'joona.sipilä37@example.com','Joona Sipilä','+358400000037','Suomi',0),(38,'karoliina.puro38@example.com','Karoliina Puro','+358400000038','Suomi',0),(39,'janne.savela39@example.com','Janne Sävelä','+358400000039','Suomi',0),(40,'finteco.ltd@example.com','Finteco Ltd','+358400000040','Suomi',1),(41,'ville.ylitalo41@example.com','Ville Ylitalo','+358400000041','Suomi',0),(42,'satu.karvonen42@example.com','Satu Karvonen','+358400000042','Suomi',0),(43,'niklas.eskola43@example.com','Niklas Eskola','+358400000043','Suomi',0),(44,'emilia.kivinen44@example.com','Emilia Kivinen','+358400000044','Suomi',0),(45,'roope.laine45@example.com','Roope Laine','+358400000045','Suomi',0),(46,'kristiina.kangas46@example.com','Kristiina Kangas','+358400000046','Suomi',0),(47,'jonne.hirvonen47@example.com','Jonne Hirvonen','+358400000047','Suomi',0),(48,'paivi.hakala48@example.com','Päivi Hakala','+358400000048','Suomi',0),(49,'teemu.rautiainen49@example.com','Teemu Rautiainen','+358400000049','Suomi',0),(50,'kati.aalto50@example.com','Kati Aalto','+358400000050','Suomi',0),(51,'risto.hartikainen51@example.com','Risto Hartikainen','+358400000051','Suomi',0),(52,'tanja.nurminen52@example.com','Tanja Nurminen','+358400000052','Suomi',0),(53,'joonas.lehto53@example.com','Joonas Lehto','+358400000053','Suomi',0),(54,'katariina.turunen54@example.com','Katariina Turunen','+358400000054','Suomi',0),(55,'tuomas.saaristo55@example.com','Tuomas Saaristo','+358400000055','Suomi',0),(56,'anu.peltola56@example.com','Anu Peltola','+358400000056','Suomi',0),(57,'matias.lindroos57@example.com','Matias Lindroos','+358400000057','Suomi',0),(58,'jenna.ranta58@example.com','Jenna Ranta','+358400000058','Suomi',0),(59,'mikko.hartikainen59@example.com','Mikko Hartikainen','+358400000059','Suomi',0),(60,'nordek.ab@example.com','Nordek Ab','+358400000060','Suomi',1),(61,'jukka.hannula60@example.com','Jukka Hannla','+358400000061','Suomi',0),(62,'testi@testi.fi','Nimi Sukunimi','+358400000062','Suomi',0);
/*!40000 ALTER TABLE `asiakas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kayttaja`
--

DROP TABLE IF EXISTS `kayttaja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kayttaja` (
  `id` int NOT NULL AUTO_INCREMENT,
  `kayttaja_nimi` varchar(45) NOT NULL,
  `salasana` varchar(45) NOT NULL,
  `is_admin` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `kayttaja_nimi_UNIQUE` (`kayttaja_nimi`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kayttaja`
--

LOCK TABLES `kayttaja` WRITE;
/*!40000 ALTER TABLE `kayttaja` DISABLE KEYS */;
INSERT INTO `kayttaja` VALUES (1,'guest','guest',0),(2,'nimi','salasana',1),(3,'jaa','123',1);
/*!40000 ALTER TABLE `kayttaja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lasku`
--

DROP TABLE IF EXISTS `lasku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lasku` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hinta` float NOT NULL,
  `laskutustapa` varchar(255) NOT NULL,
  `erapaiva` date NOT NULL,
  `tila` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lasku`
--

LOCK TABLES `lasku` WRITE;
/*!40000 ALTER TABLE `lasku` DISABLE KEYS */;
/*!40000 ALTER TABLE `lasku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mokki`
--

DROP TABLE IF EXISTS `mokki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mokki` (
  `id` int NOT NULL AUTO_INCREMENT,
  `osoite` varchar(255) NOT NULL,
  `tila` tinyint NOT NULL,
  `koko` int NOT NULL,
  `huoneet` int NOT NULL,
  `hinta_per_yo` float NOT NULL,
  `luotu` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paivitetty` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mokki`
--

LOCK TABLES `mokki` WRITE;
/*!40000 ALTER TABLE `mokki` DISABLE KEYS */;
INSERT INTO `mokki` VALUES (1,'Anttilantie 25 (Savonranta)',0,91,3,248.8,'2025-05-14 16:22:31','2025-05-14 14:15:31'),(2,'Kalliontie 11 (Varkaus)',0,68,3,169.95,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(3,'Yliopistokatu 36 (Kuopio)',0,55,2,240.64,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(4,'Kirkonkyläntie 57 (Pielavesi)',0,149,6,248.39,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(5,'Hatanpäänkatu 45 (Tampere)',0,50,6,184.61,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(6,'Suvannonpolku 3 (Kaavi)',0,59,5,180.65,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(7,'Kumpulantie 6 (Kitee)',0,146,3,140.04,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(8,'Kettuvaarantie 15 (Tervo)',0,143,6,168.97,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(9,'Suvannonpolku 3 (Kaavi)',0,84,2,181.81,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(10,'Kontiontie 8 (Pohjois-Karjala)',0,105,4,52.13,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(11,'Kiinteistötie 7 (Varkaus)',0,121,3,234.77,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(12,'Kartanontie 10 (Kitee)',0,107,3,227.94,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(13,'Kalevankatu 28 (Kuopio)',0,81,2,133.17,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(14,'Hakamäentie 22 (Varkaus)',0,100,2,234.66,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(15,'Kiinteistötie 7 (Varkaus)',0,66,3,221.08,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(16,'Anttilantie 25 (Savonranta)',0,50,4,62.96,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(17,'Kettuvaarantie 15 (Tervo)',0,74,6,73.15,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(18,'Kirkonkyläntie 57 (Pielavesi)',0,112,4,93.38,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(19,'Talvitie 10 (Kangasniemi)',0,40,6,96.75,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(20,'Kirkonkyläntie 57 (Pielavesi)',0,86,2,188.31,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(21,'Pitkäjärventie 10 (Savonlinna)',0,59,6,228.39,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(22,'Hatanpäänkatu 45 (Tampere)',0,109,6,62.26,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(23,'Petkeljärventie 6 (Suomussalmi)',0,99,3,166.67,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(24,'Pitkäjärventie 5 (Vimpeli)',0,142,5,160.61,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(25,'Pitkäjärventie 5 (Vimpeli)',0,43,5,112.99,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(26,'Virransalmentie 25 (Lappeenranta)',0,62,2,65.21,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(27,'Kalliontie 11 (Varkaus)',0,104,6,98.9,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(28,'Talvivaara 18 (Lieksa)',0,101,4,192.68,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(29,'Kartanontie 10 (Kitee)',0,119,5,159.53,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(30,'Yliopistokatu 36 (Kuopio)',0,99,6,156.48,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(31,'Rantakatu 12 (Joensuu)',0,47,3,216.12,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(32,'Metsolantie 18 (Siilinjärvi)',0,117,3,189.38,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(33,'Kettuvaarantie 12 (Vesanto)',0,55,2,123.05,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(34,'Lönnrotinkatu 15 (Savonlinna)',0,81,2,121.49,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(35,'Hakalanmäentie 8 (Kitee)',0,117,5,64,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(36,'Puijo 21 (Joensuu)',0,136,6,174.17,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(37,'Talvitie 10 (Kangasniemi)',0,57,4,83.71,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(38,'Pitkäjärventie 10 (Savonlinna)',0,144,4,219.84,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(39,'Puijo 21 (Joensuu)',0,125,2,140.82,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(40,'Kettuvaarantie 12 (Vesanto)',0,100,6,110.24,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(41,'Rautjärventie 23 (Rautjärvi)',0,56,5,59.32,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(42,'Lapinpolku 21 (Savukoski)',0,32,2,238.97,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(43,'Laukkavedenkatu 11 (Varkaus)',0,33,2,98.56,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(44,'Hakamäentie 22 (Varkaus)',0,50,6,147.51,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(45,'Särkilahdentie 5 (Lieksa)',0,132,2,89.9,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(46,'Suviharjuntie 28 (Nilsiä)',0,98,4,212.24,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(47,'Kalliontie 11 (Varkaus)',0,91,5,189.11,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(48,'Laukkavedenkatu 11 (Varkaus)',0,79,4,140.62,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(49,'Virransalmentie 25 (Lappeenranta)',0,140,4,98.03,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(50,'Vuoksenniskantie 22 (Imatra)',0,81,6,55.3,'2025-05-14 16:22:31','2025-05-14 13:22:31'),(51,'Koivuranta 1 (Savonlinna)',0,75,3,95.5,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(52,'Metsäpolku 12 (Joensuu)',0,62,2,85,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(53,'Järvitie 5 (Kuopio)',0,90,4,110.75,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(54,'Hirsimökki 8 (Outokumpu)',0,58,2,70,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(55,'Saunatie 3 (Varkaus)',0,80,3,98,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(56,'Lehtoniemi 14 (Pieksämäki)',0,67,3,89.5,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(57,'Rantakallio 22 (Savonranta)',0,93,4,115,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(58,'Pihapolku 7 (Nilsiä)',0,60,2,82,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(59,'Männikkötie 9 (Iisalmi)',0,78,3,99.9,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(60,'Aurinkorinne 16 (Siilinjärvi)',0,85,3,105,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(61,'Katajakuja 4 (Heinävesi)',0,72,3,94,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(62,'Lammenranta 11 (Rantasalmi)',0,69,2,87.5,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(63,'Rovastintie 6 (Leppävirta)',0,88,4,108,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(64,'Harjutie 2 (Juankoski)',0,63,2,83,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(65,'Kuusikkopolku 17 (Enonkoski)',0,77,3,96,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(66,'Rantatie 13 (Sulkava)',0,92,4,112,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(67,'Huvilatie 19 (Kaavi)',0,66,3,90,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(68,'Saarikatu 21 (Kitee)',0,70,2,88,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(69,'Vihertie 10 (Lieksa)',0,74,3,93.5,'2025-05-14 16:41:13','2025-05-14 13:41:13'),(70,'Laiturikuja 23 (Nurmes)',0,86,3,106.25,'2025-05-14 16:41:13','2025-05-14 13:41:13');
/*!40000 ALTER TABLE `mokki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varaus`
--

DROP TABLE IF EXISTS `varaus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `varaus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `aloituspaiva` date NOT NULL,
  `lopetuspaiva` date NOT NULL,
  `luotu` datetime NOT NULL,
  `paivitetty` datetime NOT NULL,
  `asiakas_id` int NOT NULL,
  `mokki_id` int NOT NULL,
  `lasku_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `varaus_asiakas_id_foreign_idx` (`asiakas_id`),
  KEY `varaus_mokki_id_foreign_idx` (`mokki_id`),
  KEY `varaus_lasku_id_foreign` (`lasku_id`),
  CONSTRAINT `varaus_asiakas_id_foreign` FOREIGN KEY (`asiakas_id`) REFERENCES `asiakas` (`id`),
  CONSTRAINT `varaus_lasku_id_foreign` FOREIGN KEY (`lasku_id`) REFERENCES `lasku` (`id`) ON DELETE CASCADE,
  CONSTRAINT `varaus_mokki_id_foreign` FOREIGN KEY (`mokki_id`) REFERENCES `mokki` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varaus`
--

LOCK TABLES `varaus` WRITE;
/*!40000 ALTER TABLE `varaus` DISABLE KEYS */;
/*!40000 ALTER TABLE `varaus` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-14 17:55:00

-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: construcaocivil
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipo_cliente` varchar(45) NOT NULL,
  `cpf_cnpj` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `id_cliente_UNIQUE` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'cliente 01','1','090900909','0909090');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etapa`
--

DROP TABLE IF EXISTS `etapa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etapa` (
  `id_etapa` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `data_inicio` varchar(45) NOT NULL,
  `data_fim_prevista` varchar(45) NOT NULL,
  `fk_projeto_etapa` int(11) NOT NULL,
  PRIMARY KEY (`id_etapa`),
  UNIQUE KEY `id_etapa_UNIQUE` (`id_etapa`),
  KEY `fk_tb_etapa_tb_projeto1_idx` (`fk_projeto_etapa`),
  CONSTRAINT `fk_tb_etapa_tb_projeto1` FOREIGN KEY (`fk_projeto_etapa`) REFERENCES `projeto` (`id_projeto`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etapa`
--

LOCK TABLES `etapa` WRITE;
/*!40000 ALTER TABLE `etapa` DISABLE KEYS */;
/*!40000 ALTER TABLE `etapa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `id_material` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `unidade_medida` varchar(45) NOT NULL,
  `preco_unitario` double NOT NULL,
  PRIMARY KEY (`id_material`),
  UNIQUE KEY `id_material_UNIQUE` (`id_material`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profissional`
--

DROP TABLE IF EXISTS `profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profissional` (
  `id_profissional` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cargo` varchar(45) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  PRIMARY KEY (`id_profissional`),
  UNIQUE KEY `id_profissional_UNIQUE` (`id_profissional`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profissional`
--

LOCK TABLES `profissional` WRITE;
/*!40000 ALTER TABLE `profissional` DISABLE KEYS */;
INSERT INTO `profissional` VALUES (1,'david ','chefe','080808','909090909');
/*!40000 ALTER TABLE `profissional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto`
--

DROP TABLE IF EXISTS `projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto` (
  `id_projeto` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `data_inicio` varchar(45) NOT NULL,
  `data_fim_prevista` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `fk_cliente_projeto` int(11) NOT NULL,
  `fk_profissional_projetol` int(11) NOT NULL,
  PRIMARY KEY (`id_projeto`),
  UNIQUE KEY `id_projeto_UNIQUE` (`id_projeto`),
  KEY `fk_tb_projeto_tb_cliente1_idx` (`fk_cliente_projeto`),
  KEY `fk_tb_projeto_tb_profissional1_idx` (`fk_profissional_projetol`),
  CONSTRAINT `fk_tb_projeto_tb_cliente1` FOREIGN KEY (`fk_cliente_projeto`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE,
  CONSTRAINT `fk_tb_projeto_tb_profissional1` FOREIGN KEY (`fk_profissional_projetol`) REFERENCES `profissional` (`id_profissional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto`
--

LOCK TABLES `projeto` WRITE;
/*!40000 ALTER TABLE `projeto` DISABLE KEYS */;
/*!40000 ALTER TABLE `projeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uso_material`
--

DROP TABLE IF EXISTS `uso_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uso_material` (
  `id_uso_material` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` double NOT NULL,
  `fk_material_uso_material` int(11) NOT NULL,
  `fk_etapa_uso_material` int(11) NOT NULL,
  PRIMARY KEY (`id_uso_material`),
  UNIQUE KEY `id_uso_material_UNIQUE` (`id_uso_material`),
  KEY `fk_tb_uso_material_tb_material1_idx` (`fk_material_uso_material`),
  KEY `fk_tb_uso_material_tb_etapa1_idx` (`fk_etapa_uso_material`),
  CONSTRAINT `fk_tb_uso_material_tb_etapa1` FOREIGN KEY (`fk_etapa_uso_material`) REFERENCES `etapa` (`id_etapa`) ON UPDATE CASCADE,
  CONSTRAINT `fk_tb_uso_material_tb_material1` FOREIGN KEY (`fk_material_uso_material`) REFERENCES `material` (`id_material`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uso_material`
--

LOCK TABLES `uso_material` WRITE;
/*!40000 ALTER TABLE `uso_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `uso_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'construcaocivil'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-09 19:41:57

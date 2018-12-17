-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: db_consulta_medicas
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `consulta` (
  `idConsulta` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `horario` time NOT NULL,
  `idMedico` int(11) NOT NULL,
  `nomePaciente` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `rgPaciente` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `idEspecialidade` int(11) NOT NULL,
  PRIMARY KEY (`idConsulta`),
  KEY `fkConsultaMedico_idx` (`idMedico`),
  KEY `fkConsultaEspecialidade_idx` (`idEspecialidade`),
  CONSTRAINT `fkConsultaEspecialidade` FOREIGN KEY (`idEspecialidade`) REFERENCES `especialidade` (`idespecialidade`),
  CONSTRAINT `fkConsultaMedico` FOREIGN KEY (`idMedico`) REFERENCES `medico` (`idmedico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dia_semana`
--

DROP TABLE IF EXISTS `dia_semana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dia_semana` (
  `idDiaSemana` int(11) NOT NULL AUTO_INCREMENT,
  `diaSemana` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idDiaSemana`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia_semana`
--

LOCK TABLES `dia_semana` WRITE;
/*!40000 ALTER TABLE `dia_semana` DISABLE KEYS */;
INSERT INTO `dia_semana` VALUES (1,'Domingo'),(2,'Segunda - feira'),(3,'Terça - feira'),(4,'Quarta - feira'),(5,'Quinta - feira'),(6,'Sexta - feira'),(7,'Sábado');
/*!40000 ALTER TABLE `dia_semana` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidade`
--

DROP TABLE IF EXISTS `especialidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `especialidade` (
  `idEspecialidade` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `descricao` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idEspecialidade`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidade`
--

LOCK TABLES `especialidade` WRITE;
/*!40000 ALTER TABLE `especialidade` DISABLE KEYS */;
INSERT INTO `especialidade` VALUES (1,'Ortopedia','O Ortopedista é o profissional da \nmedicina especializado no diagnóstico\ne tratamento de lesões e disfunções no \nsistema locomotor, ou seja, nos músculos \ne ossos das mãos, braços, pés, pernas, coluna, quadril, etc.'),(2,'Neurologia','A Neurologia é a especialidade que se\ndedica ao diagnóstico e tratamento das \ndoenças que afetam o sistema nervoso \n(cérebro, tronco encefálico, cerebelo, \nmedula espinhal e nervos) e os componentes \nda junção neuromuscular (nervo e músculos).'),(7,'Gastroenterologia','É o estudo, diagnóstico, tratamento e prevenção de doenças relacionadas ao aparelho digestivo, desde erros inatos do metabolismo, doença do trato gastrointestinal, doenças do fígado e cânceres.'),(8,'Cardiologia','Aborda as doenças relacionadas com o coração e sistema vascular.'),(9,'Infectologia','Prevenção, diagnóstico e tratamentos de infecções\n causadas por vírus, bactérias, fungos e parasitas (helmintologia, protozoologia, entomologia e artropodologia).'),(10,'Coloproctologia ','É a parte da medicina que estuda e trata\nos problemas do intestino grosso (cólon),\nsigmóide e doenças do reto, canal anal e ânus.'),(11,'Dermatologia','É o estudo da pele, anexos da pele e suas doenças.');
/*!40000 ALTER TABLE `especialidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidade_medico`
--

DROP TABLE IF EXISTS `especialidade_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `especialidade_medico` (
  `idMedico` int(11) NOT NULL,
  `idEspecialidade` int(11) NOT NULL,
  KEY `fkEspecialidadeMedicoMedico_idx` (`idMedico`),
  KEY `fkEspecialidadeMedicoEspecialidade_idx` (`idEspecialidade`),
  CONSTRAINT `fkEspecialidadeMedicoEspecialidade` FOREIGN KEY (`idEspecialidade`) REFERENCES `especialidade` (`idespecialidade`) ON DELETE CASCADE,
  CONSTRAINT `fkEspecialidadeMedicoMedico` FOREIGN KEY (`idMedico`) REFERENCES `medico` (`idmedico`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidade_medico`
--

LOCK TABLES `especialidade_medico` WRITE;
/*!40000 ALTER TABLE `especialidade_medico` DISABLE KEYS */;
INSERT INTO `especialidade_medico` VALUES (1,1),(1,2),(2,1),(1,7),(1,9),(1,7),(1,9),(5,8);
/*!40000 ALTER TABLE `especialidade_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario_atendimento`
--

DROP TABLE IF EXISTS `horario_atendimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `horario_atendimento` (
  `idHorarioAtendimento` int(11) NOT NULL AUTO_INCREMENT,
  `horario_inicio` time NOT NULL,
  `horario_fim` time NOT NULL,
  `idDiaSemana` int(11) NOT NULL,
  `idMedico` int(11) NOT NULL,
  PRIMARY KEY (`idHorarioAtendimento`),
  KEY `fkHorarioAtendimentoDia_idx` (`idDiaSemana`),
  KEY `fkHorarioAtendimentoMedico_idx` (`idMedico`),
  CONSTRAINT `fkHorarioAtendimentoDia` FOREIGN KEY (`idDiaSemana`) REFERENCES `dia_semana` (`iddiasemana`),
  CONSTRAINT `fkHorarioAtendimentoMedico` FOREIGN KEY (`idMedico`) REFERENCES `medico` (`idmedico`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario_atendimento`
--

LOCK TABLES `horario_atendimento` WRITE;
/*!40000 ALTER TABLE `horario_atendimento` DISABLE KEYS */;
INSERT INTO `horario_atendimento` VALUES (1,'09:00:00','14:00:00',2,1),(2,'12:00:00','18:00:00',3,1),(3,'10:00:00','14:00:00',4,1),(4,'14:00:00','18:00:00',5,1),(5,'09:00:00','15:00:00',6,1),(6,'13:00:00','16:00:00',2,2),(7,'16:00:00','18:00:00',3,2),(8,'12:00:00','17:00:00',4,2),(9,'08:00:00','14:00:00',5,2),(10,'14:00:00','18:00:00',6,2),(11,'17:00:00','21:00:00',1,5),(12,'12:00:00','21:00:00',2,5),(13,'14:00:00','19:00:00',3,5),(14,'17:00:00','21:00:00',4,5),(15,'15:00:00','20:00:00',5,5),(16,'17:00:00','21:00:00',6,5),(17,'19:00:00','21:00:00',7,5);
/*!40000 ALTER TABLE `horario_atendimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `medico` (
  `idMedico` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `rg` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `crm` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idMedico`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
INSERT INTO `medico` VALUES (1,'Joaquim','54.126.201-7','10101010-1/SP'),(2,'Matheus','33.104.307-5','20202020-2/SP'),(5,'Mariano','12.555.132-9','12345678-9/DF');
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-14 14:02:49

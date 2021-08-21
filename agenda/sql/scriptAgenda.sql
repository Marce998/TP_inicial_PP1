CREATE DATABASE [IF NOT EXISTS] `agenda`;
USE agenda;
DROP TABLE [IF EXISTS] `personas`
CREATE TABLE `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Email` varchar(20) NOT NULL, 
  `fechaNac` date NOT NULL,
  `Domicilio` varchar(100) NOT NULL,
  `Tipo` varchar(20) NOT NULL,
  PRIMARY KEY (`idPersona`)
);
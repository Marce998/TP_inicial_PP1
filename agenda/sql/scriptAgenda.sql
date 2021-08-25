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
  `Pais` varchar(30) NOT NULL,
  `Provincia` varchar(30) NOT NULL,
  `Localidad` varchar(30) NOT NULL,
  PRIMARY KEY (`idPersona`)
);

CREATE TABLE `tipoContacto`
(
`idTipoContacto` int(11) NOT NULL AUTO_INCREMENT,
`tipo` varchar(45) NOT NULL,
PRIMARY KEY (`idTipoContacto`)
);

INSERT INTO tipocontacto VALUES(1,"Amigo");
INSERT INTO tipocontacto VALUES (2,"Trabajo");
INSERT INTO tipocontacto VALUES (3,"Familia");

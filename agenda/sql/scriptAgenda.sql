CREATE DATABASE IF NOT EXISTS `agenda`;
USE agenda;
DROP TABLE IF EXISTS `personas`;
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
  `ciudadPreferida` longtext NOT NULL,
  PRIMARY KEY (`idPersona`)
);

DROP TABLE IF EXISTS `tipocontacto`;

CREATE TABLE `tipocontacto`
(
`idTipoContacto` int(11) NOT NULL AUTO_INCREMENT,
`tipo` varchar(45) NOT NULL,
PRIMARY KEY (`idTipoContacto`)
);

DROP TABLE IF EXISTS `localidades`;
DROP TABLE IF EXISTS `provincias`;
DROP TABLE IF EXISTS `paises`;

CREATE TABLE `paises`(
	`id_pais` INT(11) AUTO_INCREMENT NOT NULL,
    `nombre` VARCHAR(45) NOT NULL,
    primary key(`id_pais`)
);

CREATE TABLE `provincias`(
	`id_provincia` INT(11) AUTO_INCREMENT NOT NULL,
	`nombre` VARCHAR(45) NOT NULL,
    `id_pais` INT(11) NOT NULL,
    PRIMARY KEY(`id_provincia`),
	FOREIGN KEY(`id_pais`) REFERENCES paises (`id_pais`)
);

CREATE TABLE `localidades`(
	`id_localidad` int(11) AUTO_INCREMENT NOT NULL,
    `nombre` VARCHAR (45) NOT NULL,
    `id_provincia` INT(11),
	PRIMARY KEY(`id_localidad`),
    CONSTRAINT FOREIGN KEY(`id_provincia`) REFERENCES provincias (`id_provincia`)
);

INSERT INTO tipocontacto VALUES(1,"Amigo");
INSERT INTO tipocontacto VALUES (2,"Trabajo");
INSERT INTO tipocontacto VALUES (3,"Familia");

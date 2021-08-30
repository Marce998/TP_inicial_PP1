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

INSERT INTO paises VALUES(1,"Argentina");
INSERT INTO paises VALUES(2,"Uruguay");
INSERT INTO paises VALUES(3,"Brasil");

INSERT INTO provincias VALUES (1, 'Buenos Aires',1),
(2, 'Catamarca',1),
(3, 'Chaco',1),
(4, 'Chubut',1),
(5, 'Córdoba',1),
(6, 'Corrientes',1),
(7, 'Entre Ríos',1),
(8, 'Formosa',1),
(9, 'Jujuy',1),
(10, 'La Pampa',1),
(11, 'La Rioja',1),
(12, 'Mendoza',1),
(13, 'Misiones',1),
(14, 'Neuquén',1),
(15, 'Río Negro',1),
(16, 'Salta',1),
(17, 'San Juan',1),
(18, 'San Luis',1),
(19, 'Santa Cruz',1),
(20, 'Santa Fe',1),
(21, 'Santiago del Estero',1),
(22, 'Tierra del Fuego',1),
(23, 'Tucumán',1);

INSERT INTO localidades VALUES(1,"San Miguel",1);
INSERT INTO localidades VALUES(2,"Malvinas Argentinas",1);
INSERT INTO localidades VALUES(3,"José C. Paz",1);
INSERT INTO localidades VALUES(4,"Moreno",1);
INSERT INTO localidades VALUES(5,"San Fernando del Valle de Catamarca",2);
INSERT INTO localidades VALUES(6,"Resistencia",3);
INSERT INTO localidades VALUES(7,"Rawson",4);
INSERT INTO localidades VALUES(8,"Puerto Madryn",4);
INSERT INTO localidades VALUES(9,"Córdiba",5);
INSERT INTO localidades VALUES(10,"Río Cuarto",5);
INSERT INTO localidades VALUES(11,"Villa Carlos Paz",5);
INSERT INTO localidades VALUES(12,"Cosquín",5);
INSERT INTO localidades VALUES(13,"Corrientes",6);
INSERT INTO localidades VALUES(14,"Paraná",7);
INSERT INTO localidades VALUES(15,"Gualeguay",7);
INSERT INTO localidades VALUES(16,"Gualeguaychú",7);
INSERT INTO localidades VALUES(17,"Formosa",8);
INSERT INTO localidades VALUES(18,"San Salvador de Jujuy",9);
INSERT INTO localidades VALUES(19,"Santa Rosa",10);
INSERT INTO localidades VALUES(20,"La Rioja",11);
INSERT INTO localidades VALUES(21,"Mendoza",12);
INSERT INTO localidades VALUES(22,"Godoy Cruz",12);
INSERT INTO localidades VALUES(23,"Posadas",13);
INSERT INTO localidades VALUES(24,"Iguasú",13);
INSERT INTO localidades VALUES(25,"Neuquén",14);
INSERT INTO localidades VALUES(26,"Villa La Angostura",14);
INSERT INTO localidades VALUES(27,"Viedma",15);
INSERT INTO localidades VALUES(28,"Bariloche",15);
INSERT INTO localidades VALUES(29,"Salta",16);
INSERT INTO localidades VALUES(30,"San Juan",17);
INSERT INTO localidades VALUES(31,"San Luis",18);
INSERT INTO localidades VALUES(32,"Santa Fe",20);
INSERT INTO localidades VALUES(33,"Rosario",20);
INSERT INTO localidades VALUES(34,"Rafaela",20);
INSERT INTO localidades VALUES(35,"Venado Tuerto",20);
INSERT INTO localidades VALUES(36,"Río Gallegos",19);
INSERT INTO localidades VALUES(37,"Santiago Del Estero",21);
INSERT INTO localidades VALUES(38,"Ushuaia",22);
INSERT INTO localidades VALUES(39,"San Miguel de Tucumán",23);

INSERT INTO provincias VALUES (24,"Montevideo",2),
(25,"Canelones",2),
(26,"Colonia",2),
(27,"Durazno",2),
(28,"Paysandú",2),
(29,"Salto",2),
(30,"Maldonado",2);

INSERT INTO localidades VALUES(40,"Montevideo",24);
INSERT INTO localidades VALUES(41,"Canelones",25);
INSERT INTO localidades VALUES(42,"Colonia del Sacramento",26);
INSERT INTO localidades VALUES(43,"Durazno",27);
INSERT INTO localidades VALUES(44,"Paysandú",28);
INSERT INTO localidades VALUES(45,"Salto",29);
INSERT INTO localidades VALUES(46,"Maldonado",30);

INSERT INTO provincias VALUES 
(31, 'Amazonas', 3),
(32, 'Bahia',3),
(33, 'Goiás', 3),
(34, 'Pernambuco',3),
(35, 'Rio de Janeiro',3),
(36, 'Sao Paulo',3);

INSERT INTO localidades VALUES(47,"Manaos",31),
(48,"Salvador",32),
(49,"Goiania",33),
(50,"Recife",34),
(51,"Rio de Janeiro",35),
(52,"Sao Paulo",36);

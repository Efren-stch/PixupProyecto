-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pixup
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `pixup` ;

-- -----------------------------------------------------
-- Schema pixup
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pixup` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `pixup` ;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_ESTADO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_ESTADO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_ESTADO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_MUNICIPIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_MUNICIPIO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_MUNICIPIO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `idEstado` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Municipio_Estado`
    FOREIGN KEY (`idEstado`)
    REFERENCES `pixup`.`TBL_ESTADO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Municipio_Estado_idx` ON `pixup`.`TBL_MUNICIPIO` (`idEstado` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_COLONIA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_COLONIA` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_COLONIA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `idMunicipio` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Colonia_Municipio1`
    FOREIGN KEY (`idMunicipio`)
    REFERENCES `pixup`.`TBL_MUNICIPIO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Colonia_Municipio1_idx` ON `pixup`.`TBL_COLONIA` (`idMunicipio` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_ARTISTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_ARTISTA` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_ARTISTA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_DISQUERA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_DISQUERA` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_DISQUERA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_GENEROMUSICAL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_GENEROMUSICAL` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_GENEROMUSICAL` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_DISCO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_DISCO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_DISCO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DOUBLE(4,2) NOT NULL,
  `existencia` INT NOT NULL,
  `descuento` DOUBLE(2,2) NOT NULL,
  `fechaLanzamiento` VARCHAR(45) NOT NULL,
  `imagen` VARCHAR(120) NOT NULL,
  `idArtista` INT NOT NULL,
  `idDisquera` INT NOT NULL,
  `idGeneroMusical` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Disco_Artista1`
    FOREIGN KEY (`idArtista`)
    REFERENCES `pixup`.`TBL_ARTISTA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Disco_Disquera1`
    FOREIGN KEY (`idDisquera`)
    REFERENCES `pixup`.`TBL_DISQUERA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Disco_GeneroMusical1`
    FOREIGN KEY (`idGeneroMusical`)
    REFERENCES `pixup`.`TBL_GENEROMUSICAL` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Disco_Artista1_idx` ON `pixup`.`TBL_DISCO` (`idArtista` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `fk_Disco_Disquera1_idx` ON `pixup`.`TBL_DISCO` (`idDisquera` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `fk_Disco_GeneroMusical1_idx` ON `pixup`.`TBL_DISCO` (`idGeneroMusical` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_CANCION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_CANCION` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_CANCION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(150) NOT NULL,
  `duracion` DOUBLE(3,2) NOT NULL,
  `idDisco` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Cancion_Disco1`
    FOREIGN KEY (`idDisco`)
    REFERENCES `pixup`.`TBL_DISCO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_Cancion_Disco1_idx` ON `pixup`.`TBL_CANCION` (`idDisco` ASC) VISIBLE;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ifinances
-- -----------------------------------------------------
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema petstoredb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `petstoredb` ;

-- -----------------------------------------------------
-- Schema petstoredb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `petstoredb` DEFAULT CHARACTER SET latin1 ;
SHOW WARNINGS;
USE `petstoredb` ;

-- -----------------------------------------------------
-- Table `petstoredb`.`CATEGORY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petstoredb`.`CATEGORY` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `petstoredb`.`CATEGORY` (
  `CATEGORY_ID` BIGINT NOT NULL,
  `CATEGORY_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`),
  UNIQUE INDEX `CATEGORY_ID_UNIQUE` (`CATEGORY_ID` ASC),
  UNIQUE INDEX `CATEGORY_NAME_UNIQUE` (`CATEGORY_NAME` ASC))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `petstoredb`.`PET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petstoredb`.`PET` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `petstoredb`.`PET` (
  `PET_ID` BIGINT NOT NULL,
  `NAME` VARCHAR(200) NOT NULL,
  `STATUS` VARCHAR(20) NOT NULL,
  `CATEGORY_ID_FK` BIGINT NOT NULL,
  PRIMARY KEY (`PET_ID`),
  UNIQUE INDEX `PET_ID_UNIQUE` (`PET_ID` ASC),
  INDEX `fk_PET_CATEGORY_idx` (`CATEGORY_ID_FK` ASC),
  CONSTRAINT `fk_PET_CATEGORY`
    FOREIGN KEY (`CATEGORY_ID_FK`)
    REFERENCES `petstoredb`.`CATEGORY` (`CATEGORY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `petstoredb`.`PHOTO_URL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petstoredb`.`PHOTO_URL` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `petstoredb`.`PHOTO_URL` (
  `PHOTO_URL_ID` BIGINT NOT NULL,
  `URL` VARCHAR(4000) NOT NULL DEFAULT 'http://localhost:8080/photoUrl/default/ImageNotFound.jpg',
  `PET_ID_FK` BIGINT NOT NULL,
  PRIMARY KEY (`PHOTO_URL_ID`),
  UNIQUE INDEX `PHOTO_URL_ID_UNIQUE` (`PHOTO_URL_ID` ASC),
  INDEX `fk_PHOTO_URL_PET_idx` (`PET_ID_FK` ASC),
  CONSTRAINT `fk_PHOTO_URLS_PET1`
    FOREIGN KEY (`PET_ID_FK`)
    REFERENCES `petstoredb`.`PET` (`PET_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `petstoredb`.`TAG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petstoredb`.`TAG` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `petstoredb`.`TAG` (
  `TAG_ID` BIGINT NOT NULL,
  `TAG_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TAG_ID`),
  UNIQUE INDEX `TAG_ID_UNIQUE` (`TAG_ID` ASC),
  UNIQUE INDEX `TAG_NAME_UNIQUE` (`TAG_NAME` ASC))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `petstoredb`.`MTM_PET_TAG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petstoredb`.`MTM_PET_TAG` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `petstoredb`.`MTM_PET_TAG` (
  `TAG_ID_FK` BIGINT NOT NULL,
  `PET_ID_FK` BIGINT NOT NULL,
  PRIMARY KEY (`TAG_ID_FK`, `PET_ID_FK`),
  INDEX `fk_PET2TAG_idx` (`PET_ID_FK` ASC),
  INDEX `fk_TAG2PET_idx` (`TAG_ID_FK` ASC),
  CONSTRAINT `fk_PET2TAG`
    FOREIGN KEY (`PET_ID_FK`)
    REFERENCES `petstoredb`.`PET` (`PET_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TAGS2PET`
    FOREIGN KEY (`TAG_ID_FK`)
    REFERENCES `petstoredb`.`TAG` (`TAG_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


CREATE USER IF NOT EXISTS 'petadmin'@'localhost' IDENTIFIED BY 'petadmin';
GRANT ALL PRIVILEGES ON *.* TO 'petadmin'@'localhost'    WITH GRANT OPTION;

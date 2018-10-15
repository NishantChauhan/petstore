-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

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
  `PET_ID` BIGINT NOT NULL auto_increment,
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
-- Table `petstoredb`.`PHOTO_URLS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petstoredb`.`PHOTO_URLS` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `petstoredb`.`PHOTO_URLS` (
  `URL` VARCHAR(4000) NOT NULL DEFAULT 'http://localhost:8080/photoUrl/default/ImageNotFound.jpg',
  `PET_ID_FK` BIGINT NOT NULL,
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

DROP TABLE IF EXISTS `petstoredb`.`user` ;

CREATE TABLE IF NOT EXISTS `petstoredb`.`user` (
  `user_id` INT NOT NULL,
  `username` VARCHAR(200) NULL,
  `password` VARCHAR(200) NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);
 
drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);
 
drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);
 
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
 
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE USER IF NOT EXISTS 'petadmin'@'localhost' IDENTIFIED BY 'petadmin';
GRANT ALL PRIVILEGES ON *.* TO 'petadmin'@'localhost'    WITH GRANT OPTION;

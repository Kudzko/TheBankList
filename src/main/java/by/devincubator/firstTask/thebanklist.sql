-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema thebanklist
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema thebanklist
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `thebanklist` DEFAULT CHARACTER SET latin1 ;
USE `thebanklist` ;

-- -----------------------------------------------------
-- Table `thebanklist`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thebanklist`.`account` (
  `idaccount` INT(11) NOT NULL AUTO_INCREMENT,
  `account` INT(11) NULL DEFAULT NULL,
  `userid` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idaccount`),
  INDEX `name` (`idaccount` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `thebanklist`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thebanklist`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `surName` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

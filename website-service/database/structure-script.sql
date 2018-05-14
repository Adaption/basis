-- -----------------------------------------------------
-- Schema website
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `website` DEFAULT CHARACTER SET utf8 ;
USE `website` ;

-- -----------------------------------------------------
-- Table `website`.`business_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `website`.`business_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `website`.`website`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `website`.`website` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `create_date` BIGINT(20) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `path` VARCHAR(255) NULL DEFAULT NULL,
  `logo` VARCHAR(255) NULL DEFAULT NULL,
  `copyright` VARCHAR(45) NULL DEFAULT NULL,
  `status` BIT(1) NOT NULL,
  `business_type_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_website_business_type_idx` (`business_type_id` ASC),
  CONSTRAINT `fk_website_business_type`
    FOREIGN KEY (`business_type_id`)
    REFERENCES `website`.`business_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `website`.`domain`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `website`.`domain` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NOT NULL,
  `type` BIT(1) NOT NULL,
  `status` BIT(1) NOT NULL,
  `website_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_domain_website1_idx` (`website_id` ASC),
  CONSTRAINT `fk_domain_website1`
    FOREIGN KEY (`website_id`)
    REFERENCES `website`.`website` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `website`.`page`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `website`.`page` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `create_date` BIGINT(20) NULL DEFAULT NULL,
  `path` VARCHAR(255) NULL DEFAULT NULL,
  `home_page` BIT(1) NOT NULL,
  `website_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_page_website1_idx` (`website_id` ASC),
  CONSTRAINT `fk_page_website1`
    FOREIGN KEY (`website_id`)
    REFERENCES `website`.`website` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;
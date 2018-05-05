
-- -----------------------------------------------------
-- Schema user
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `user` DEFAULT CHARACTER SET utf8 ;
USE `user` ;

-- -----------------------------------------------------
-- Table `user`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `address` VARCHAR(50) NULL,
  `image_profile` VARCHAR(100) NOT NULL,
  `full_name` VARCHAR(100) NOT NULL,
  `status` BIT NOT NULL,
  `ceate_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `user`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`.`permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user`.`permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`.`role_has_permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user`.`role_has_permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `permission_id` INT NOT NULL,
  `value` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_role_has_permission_permission1_idx` (`permission_id` ASC),
  INDEX `fk_role_has_permission_role1_idx` (`role_id` ASC),
  CONSTRAINT `fk_role_has_permission_permission1`
    FOREIGN KEY (`permission_id`)
    REFERENCES `user`.`permission` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_permission_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `user`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `paid_date` TIMESTAMP NOT NULL,
  `amount` DOUBLE NOT NULL,
  `expired_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paypal_trans_id` VARCHAR(10) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_payment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
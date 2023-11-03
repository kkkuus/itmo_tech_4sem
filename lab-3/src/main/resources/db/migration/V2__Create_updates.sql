CREATE TABLE IF NOT EXISTS `catsservice`.`fleas` (
    `FleaId` INT NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(45) NULL,
    `CatId` INT NOT NULL,
    PRIMARY KEY (`FleaId`)
);

ALTER TABLE `catsservice`.`cats`
ADD COLUMN `TailLength` INT NULL AFTER `OwnerId`;
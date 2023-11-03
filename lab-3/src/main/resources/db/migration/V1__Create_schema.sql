
CREATE TABLE IF NOT EXISTS `catsservice`.`owners` (
    `OwnerId` INT NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(45) NULL,
    `Date` DATE NULL,
    PRIMARY KEY (`OwnerId`)
);


CREATE TABLE IF NOT EXISTS `catsdatabase`.`new_table` (
    `CatId` INT NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(45) NULL,
    `Date` DATE NULL,
    `Breed` VARCHAR(45) NULL,
    `OwnerId` INT NOT NULL,
    PRIMARY KEY (`CatId`),
    FOREIGN KEY (`OwnerId`)
    REFERENCES `catsdatabase`.`owner` (`ownerId`)
);
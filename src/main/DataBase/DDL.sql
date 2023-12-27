CREATE TABLE ` consulting_room`
(
    `id`                    INT(11) NOT NULL AUTO_INCREMENT,
    `medical_office_number` VARCHAR(100) NOT NULL DEFAULT '0' COLLATE 'utf8mb4_general_ci',
    `floor`                 INT(11) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
);
CREATE TABLE `doctors`
(
    `id`               INT(11) NOT NULL AUTO_INCREMENT,
    `name`             VARCHAR(150) NOT NULL DEFAULT '0' COLLATE 'utf8mb4_general_ci',
    `last_name`        VARCHAR(100) NOT NULL COLLATE 'utf8mb4_general_ci',
    `second_last_name` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `specialite`       VARCHAR(250) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    PRIMARY KEY (`id`) USING BTREE
);
CREATE TABLE `date_medical`
(
    `id`                            INT(11) NOT NULL AUTO_INCREMENT,
    `consulting_room`               INT(11) NOT NULL DEFAULT '0',
    `id_doctor`                     INT(11) NOT NULL DEFAULT '0',
    `medical_consultation_schedule` DATETIME     NOT NULL,
    `name_of_patient`               VARCHAR(250) NOT NULL DEFAULT '0' COLLATE 'utf8mb4_general_ci',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `unique_consultation_schedule` (`consulting_room`, `medical_consultation_schedule`) USING BTREE,
    INDEX                           `Fk_consulting` (`consulting_room`) USING BTREE,
    INDEX                           `FK__doctors` (`id_doctor`) USING BTREE,
    CONSTRAINT `FK__doctors` FOREIGN KEY (`id_doctor`) REFERENCES `doctors` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `Fk_consulting` FOREIGN KEY (`consulting_room`) REFERENCES ` consulting_room` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

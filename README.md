

используется server WildFly 8.0.1 Final и база данных MySQL
создать базу данных (bulletin_board)
CREATE TABLE `advertisement` (
`id_advertisement`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
`publication_date`  bigint(11) UNSIGNED NOT NULL ,
`rubric`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`title`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`text`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`id_login`  int(11) UNSIGNED NOT NULL ,
PRIMARY KEY (`id_advertisement`),
FOREIGN KEY (`id_login`) REFERENCES `login` (`id_login`) ON DELETE NO ACTION ON UPDATE NO ACTION,
INDEX `id_login_advertisment` USING BTREE (`id_login`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='InnoDB free: 32768 kB; (`id_login`) REFER `bulliten_board/login`(`id_login`) ON '
AUTO_INCREMENT=7
ROW_FORMAT=COMPACT
;
CREATE TABLE `login` (
`id_login`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
`login`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`password`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`name_user`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id_login`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2
ROW_FORMAT=COMPACT
;
создать на servere   JNDI DataSource  java:/bulliten

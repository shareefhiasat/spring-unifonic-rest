CREATE DATABASE IF NOT EXISTS unifonic;

ALTER DATABASE unifonic
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON unifonic.* TO pc@localhost
IDENTIFIED BY 'pc';

USE unifonic;

DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS status;

CREATE TABLE IF NOT EXISTS status (
  id   INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX (name)
)
  ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS message
(
  id      INT AUTO_INCREMENT
    PRIMARY KEY,
  app_sid         VARCHAR(255)    NOT NULL,
  balance         VARCHAR(255)    NOT NULL,
  body            TEXT            NOT NULL,
  cost            DOUBLE          NOT NULL,
  date_created    DATETIME        NOT NULL,
  number_of_units VARCHAR(255)    NOT NULL,
  priority        VARCHAR(255)    NULL,
  recipient       INT             NOT NULL,
  sender_id       VARCHAR(255)    NULL,
  status          INT(4) UNSIGNED NULL,
  INDEX (app_sid),
  FOREIGN KEY (`status`) REFERENCES `status` (id)
)
  ENGINE = InnoDB;

ALTER TABLE message ALTER COLUMN status SET DEFAULT 1;




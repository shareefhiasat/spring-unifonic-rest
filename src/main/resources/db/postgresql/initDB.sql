CREATE TABLE IF NOT EXISTS status (
  status SERIAL,
  name VARCHAR(30)
);

TRUNCATE TABLE status RESTART IDENTITY;

CREATE TABLE IF NOT EXISTS message (
  id INT PRIMARY KEY NOT NULL ,
  app_sid         CHAR(255)    NOT NULL,
  balance         CHAR(255)    NOT NULL,
  body            TEXT            NOT NULL,
  cost            REAL          NOT NULL,
  date_created    DATE        NOT NULL,
  number_of_units CHAR(255)    NOT NULL,
  priority        CHAR(255),
  recipient       INT      NOT NULL,
  sender_id       CHAR(255) ,
  status          INT NULL
);

TRUNCATE TABLE message RESTART IDENTITY;

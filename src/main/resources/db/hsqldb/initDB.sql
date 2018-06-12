DROP TABLE message IF EXISTS;
DROP TABLE status IF EXISTS;

CREATE TABLE  IF NOT EXISTS status (
  id         INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);

CREATE INDEX status_name ON status (name);


CREATE TABLE  IF NOT EXISTS message (
  id         INTEGER IDENTITY PRIMARY KEY,
  app_sid         VARCHAR(255)    NOT NULL,
  balance         VARCHAR(255)    NOT NULL,
  body            VARCHAR(1000)   NOT NULL,
  cost            DOUBLE         NOT NULL,
  date_created    DATE            NOT NULL,
  number_of_units VARCHAR(255)    NOT NULL,
  priority        VARCHAR(255)    NULL,
  recipient       INTEGER         NOT NULL,
  sender_id       VARCHAR(255)    NULL,
  status          INTEGER     DEFAULT 1 NULL
);

CREATE INDEX message_app_sid ON message (app_sid);

ALTER TABLE message ADD CONSTRAINT fk_status FOREIGN KEY (status) REFERENCES status (id);


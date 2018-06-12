ALTER TABLE message AUTO_INCREMENT = 1;
ALTER TABLE status AUTO_INCREMENT = 1;
INSERT INTO status (name) VALUES ('Queued');
INSERT INTO status (name) VALUES ('Sent');
INSERT INTO status (name) VALUES ('Failed');
INSERT INTO status (name) VALUES ('Rejected');
COMMIT;

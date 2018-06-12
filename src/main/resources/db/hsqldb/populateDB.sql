ALTER TABLE status ALTER COLUMN id RESTART WITH 1;
ALTER TABLE message ALTER COLUMN id RESTART WITH 1;
INSERT INTO status (name) VALUES ('Queued');
INSERT INTO status (name) VALUES ('Sent');
INSERT INTO status (name) VALUES ('Failed');
INSERT INTO status (name) VALUES ('Rejected');

INSERT INTO projects (name) VALUES ('Project_1');
INSERT INTO projects (name) VALUES ('Project_2');

INSERT INTO devices (project_id, serial_number) VALUES (1, '4CDB');
INSERT INTO devices (project_id, serial_number) VALUES (2, '4DDB');
INSERT INTO devices (project_id, serial_number) VALUES (2, '4QDB');

INSERT INTO events (device_id, type, is_read) VALUES (1, 'WARNING', false);
INSERT INTO events (device_id, type, is_read) VALUES (1, 'EVENT', true);
INSERT INTO events (device_id, type, is_read) VALUES (1, 'ERROR', false);
INSERT INTO events (device_id, type, is_read) VALUES (1, 'WARNING', true);
INSERT INTO events (device_id, type, is_read) VALUES (1, 'WARNING', false);


INSERT INTO events (device_id, type, is_read) VALUES (2, 'EVENT', false);

INSERT INTO events (device_id, type, is_read) VALUES (3, 'WARNING', false);
INSERT INTO events (device_id, type, is_read) VALUES (3, 'WARNING', true);
INSERT INTO events (device_id, type, is_read) VALUES (3, 'WARNING', false);
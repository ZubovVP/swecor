INSERT INTO projects (name) VALUES ('Project_1');
INSERT INTO projects (name) VALUES ('Project_2');

INSERT INTO devices (project_id, serial_number) VALUES (1, '4CDB');
INSERT INTO devices (project_id, serial_number) VALUES (2, '4DDB');
INSERT INTO devices (project_id, serial_number) VALUES (2, '4QDB');

INSERT INTO events (device_id, type, is_read) VALUES (1, 'warning', true);
INSERT INTO events (device_id, type, is_read) VALUES (2, 'event', false)
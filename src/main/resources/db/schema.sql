CREATE TABLE IF NOT EXISTS  projects
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS devices
(
    id            SERIAL PRIMARY KEY,
    project_id    INT REFERENCES projects (id),
    serial_number VARCHAR(255) NOT NULL UNIQUE
);

create type type_event as enum ('EVENT', 'WARNING', 'ERROR');

CREATE TABLE IF NOT EXISTS events
(
    id        SERIAL PRIMARY KEY,
    device_id integer REFERENCES devices (id),
    data      timestamp without time zone default (now() at time zone 'utc'),
    type      type_event,
    is_read   BOOLEAN default FALSE
);
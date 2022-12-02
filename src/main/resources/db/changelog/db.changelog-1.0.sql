--liquibase formatted sql

--changeset dmoskalyuk:1
CREATE TABLE IF NOT EXISTS organizer
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE
    );

--changeset dmoskalyuk:2
CREATE TABLE IF NOT EXISTS place
(
    id SERIAL PRIMARY KEY ,
    address VARCHAR(64) NOT NULL UNIQUE
);

--changeset dmoskalyuk:3
CREATE TABLE IF NOT EXISTS event
(
    id SERIAL PRIMARY KEY ,
    title VARCHAR(64) NOT NULL UNIQUE,
    description VARCHAR(256),
    time TIMESTAMP NOT NULL ,
    organizer_id INT REFERENCES organizer(id),
    place_id INT REFERENCES place(id)
);


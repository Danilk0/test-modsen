--liquibase formatted sql

--changeset dmoskalyuk:1
ALTER TABLE event
DROP CONSTRAINT event_title_key;

--changeset dmoskalyuk:2
ALTER TABLE event
    DROP CONSTRAINT event_organizer_id_fkey,
    ADD CONSTRAINT event_organizer_id_fkey
        FOREIGN KEY (organizer_id)
            REFERENCES organizer(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE ;

--changeset dmoskalyuk:3
ALTER TABLE event
    DROP CONSTRAINT event_place_id_fkey,
    ADD CONSTRAINT event_place_id_fkey
        FOREIGN KEY (place_id)
            REFERENCES place(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE ;




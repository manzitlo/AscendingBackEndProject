CREATE TABLE events (
                        id        BIGSERIAL NOT NULL,
                        title           VARCHAR(255) NOT NULL,
                        date            DATE NOT NULL,
                        time            VARCHAR(50),
                        location        VARCHAR(255),
                        description     TEXT,
                        organizer_id    BIGINT NOT NULL
);

ALTER TABLE events ADD CONSTRAINT event_id_pk PRIMARY KEY (id);

ALTER TABLE events
    ADD CONSTRAINT organizer_event_fk FOREIGN KEY (organizer_id)
        REFERENCES users (id);
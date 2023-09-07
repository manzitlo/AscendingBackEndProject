DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
                       id               BIGSERIAL NOT NULL,
                       username         VARCHAR(30) NOT NULL UNIQUE,
                       password         VARCHAR(64) NOT NULL,
                       email            VARCHAR(50) NOT NULL UNIQUE,
                       first_name       VARCHAR(30),
                       last_name        VARCHAR(30),
                       profile_picture  VARCHAR(512),
                       bio              TEXT,
                       location         VARCHAR(255)

);

ALTER TABLE users ADD CONSTRAINT user_pk PRIMARY KEY ( id );
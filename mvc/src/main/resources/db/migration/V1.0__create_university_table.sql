CREATE TABLE universities (
      id                        BIGSERIAL NOT NULL,
      name                      VARCHAR(255) NOT NULL UNIQUE,
      location                  VARCHAR(255),
      description               TEXT,
      ranking                   BIGINT,
      website                   VARCHAR(512)
);

ALTER TABLE universities ADD CONSTRAINT university_pk PRIMARY KEY (id);
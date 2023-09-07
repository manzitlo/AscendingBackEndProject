CREATE TABLE roles (
                       id                                       BIGSERIAL NOT NULL,
                       name                                     VARCHAR(30) not null unique,
                       allowed_resource                         VARCHAR(200),
                       allowed_read                             BOOLEAN not null default false,
                       allowed_create                           BOOLEAN not null default false,
                       allowed_update                           BOOLEAN not null default false,
                       allowed_delete                           BOOLEAN not null default false,
                       allowed_upload                           BOOLEAN not null default false
);

ALTER TABLE roles ADD CONSTRAINT roles_pk PRIMARY KEY ( id );
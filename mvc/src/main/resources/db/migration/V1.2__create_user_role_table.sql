DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
    id          BIGSERIAL NOT NULL,
    name        VARCHAR(30) not null unique,
    password    VARCHAR(64),
    secret_key  varchar(512),
    first_name  VARCHAR(30),
    last_name   VARCHAR(30),
    email       VARCHAR(50) not null unique
);

ALTER TABLE users ADD CONSTRAINT user_pk PRIMARY KEY ( id );

CREATE TABLE roles (
    id                                       BIGSERIAL NOT NULL,
    name                                     VARCHAR(30) not null unique,
    allowed_resource                         VARCHAR(200),
    allowed_read                             BOOLEAN not null default false,
    allowed_create                           BOOLEAN not null default false,
    allowed_update                           BOOLEAN not null default false,
    allowed_delete                           BOOLEAN not null default false,
    allowed_upload                           BOOLEAN NOT NULL DEFAULT false,
    allowed_delete_users                     BOOLEAN NOT NULL DEFAULT false,
    allowed_manage_posts                     BOOLEAN NOT NULL DEFAULT false,
    allowed_manage_data                      BOOLEAN NOT NULL DEFAULT false,
    allowed_manage_comments                  BOOLEAN NOT NULL DEFAULT false,
    allowed_manage_events                    BOOLEAN NOT NULL DEFAULT false,
    allowed_manage_tags                      BOOLEAN NOT NULL DEFAULT false,
    allowed_manage_notifications             BOOLEAN NOT NULL DEFAULT false,
    allowed_moderate_content                 BOOLEAN NOT NULL DEFAULT false,
    allowed_analytics                        BOOLEAN NOT NULL DEFAULT false
);

ALTER TABLE roles ADD CONSTRAINT roles_pk PRIMARY KEY ( id );

CREATE TABLE users_roles (
    user_id     BIGINT NOT NULL,
    role_id     BIGINT NOT NULL
);

ALTER TABLE users_roles
    ADD CONSTRAINT users_fk FOREIGN KEY ( user_id )
        REFERENCES users ( id ) ON DELETE CASCADE;

ALTER TABLE users_roles
    ADD CONSTRAINT roles_fk FOREIGN KEY ( role_id )
        REFERENCES roles ( id );

ALTER TABLE users_roles ADD CONSTRAINT users_roles_pk PRIMARY KEY (user_id, role_id);
ALTER TABLE users_roles ADD CONSTRAINT user_role_unique UNIQUE (user_id, role_id);
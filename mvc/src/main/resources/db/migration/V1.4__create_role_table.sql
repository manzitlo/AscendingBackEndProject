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
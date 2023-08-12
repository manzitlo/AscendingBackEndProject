ALTER TABLE users_roles ADD CONSTRAINT users_roles_pk PRIMARY KEY (user_id, role_id);
ALTER TABLE users_roles ADD CONSTRAINT user_role_unique UNIQUE (user_id, role_id);
ALTER TABLE users_roles
    ADD CONSTRAINT users_fk FOREIGN KEY ( user_id )
        REFERENCES users ( id ) ON DELETE CASCADE;

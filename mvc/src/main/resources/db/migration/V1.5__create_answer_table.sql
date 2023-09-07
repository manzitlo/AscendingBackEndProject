CREATE TABLE answers (
                         id          BIGSERIAL NOT NULL,
                         content            TEXT NOT NULL,
                         created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         question_id        BIGINT NOT NULL,
                         user_id            BIGINT NOT NULL
);

ALTER TABLE answers ADD CONSTRAINT answer_id_pk PRIMARY KEY (id);

ALTER TABLE answers
    ADD CONSTRAINT question_answer_fk FOREIGN KEY (question_id)
        REFERENCES questions (id);

ALTER TABLE answers
    ADD CONSTRAINT user_answer_fk FOREIGN KEY (user_id)
        REFERENCES users (id);
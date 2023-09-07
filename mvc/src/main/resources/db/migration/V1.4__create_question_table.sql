CREATE TABLE questions (
                           id               BIGSERIAL NOT NULL,
                           content          TEXT NOT NULL,
                           created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           university_id    BIGINT NOT NULL,
                           user_id          BIGINT NOT NULL
);

ALTER TABLE questions ADD CONSTRAINT question_id_pk PRIMARY KEY (id);

ALTER TABLE questions
    ADD CONSTRAINT user_question_fk FOREIGN KEY (user_id)
        REFERENCES users (id);

ALTER TABLE questions
    ADD CONSTRAINT university_question_fk FOREIGN KEY (university_id)
        REFERENCES universities (id);
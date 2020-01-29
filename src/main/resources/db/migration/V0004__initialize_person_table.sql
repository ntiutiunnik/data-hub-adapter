CREATE TABLE person
(
    id            BIGSERIAL,
    lama_id       BIGINT       NOT NULL UNIQUE,
    office_id     BIGINT,
    uuid          VARCHAR(255) NOT NULL,
    full_name     VARCHAR(255) NOT NULL,
    primary_skill VARCHAR(255),
    active        BOOLEAN      NOT NULL,
    created       TIMESTAMP,
    updated       TIMESTAMP,

    PRIMARY KEY (id)
);

ALTER TABLE person
    ADD CONSTRAINT person__office_fkey FOREIGN KEY (office_id) REFERENCES office (id);

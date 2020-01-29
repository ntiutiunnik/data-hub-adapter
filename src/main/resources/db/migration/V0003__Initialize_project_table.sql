CREATE TABLE project
(
    id          BIGSERIAL,
    lama_id     BIGINT       NOT NULL UNIQUE,
    office_id   BIGINT,
    name        VARCHAR(255) NOT NULL,
    date_start  DATE,
    date_finish DATE,
    active      BOOLEAN      NOT NULL,
    created     TIMESTAMP,
    updated     TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE project
    ADD CONSTRAINT project__office_fkey FOREIGN KEY (office_id) REFERENCES office (id);

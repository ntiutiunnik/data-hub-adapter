CREATE TABLE membership
(
    id                      BIGSERIAL,
    lama_id                 BIGINT NOT NULL UNIQUE,
    person_id               INT    NOT NULL,
    project_id              INT    NOT NULL,
    date_start              DATE,
    date_finish             DATE,
    responsibility          VARCHAR(255),
    responsibility_category VARCHAR(255),

    PRIMARY KEY (id)
);

ALTER TABLE membership
    ADD CONSTRAINT membership__person_fkey FOREIGN KEY (person_id) REFERENCES person (id);
ALTER TABLE membership
    ADD CONSTRAINT membership__project_fkey FOREIGN KEY (project_id) REFERENCES project (id);

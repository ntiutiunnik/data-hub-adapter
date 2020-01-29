CREATE TABLE office
(
    id      BIGSERIAL,
    lama_id BIGINT       NOT NULL UNIQUE,
    city_id BIGINT,
    name    VARCHAR(255) NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE office
    ADD CONSTRAINT office__city_fkey FOREIGN KEY (city_id) REFERENCES city (id);

CREATE TABLE city
(
    id      BIGSERIAL,
    lama_id BIGINT       NOT NULL UNIQUE,
    name    VARCHAR(255) NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP,
    PRIMARY KEY (id)
);

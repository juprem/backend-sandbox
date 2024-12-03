ALTER TABLE todo
    ADD due_date TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE todo
    ADD  tags text[];

ALTER TABLE todo
    ADD is_archived BOOLEAN default false;

ALTER TABLE todo
    ADD priority SMALLINT;

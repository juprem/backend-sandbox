ALTER TABLE task
    ADD created_date TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE task
    ADD tags VARCHAR[];

ALTER TABLE task
    ADD status SMALLINT;

ALTER TABLE task
    ADD priority SMALLINT;

ALTER TABLE task
    DROP COLUMN done;
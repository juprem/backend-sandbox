CREATE TABLE task (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    name VARCHAR(124) NOT NULL,
    description VARCHAR(512) NULL,
    done BOOLEAN default false,
    todo_id VARCHAR(36)
);

ALTER TABLE task ADD CONSTRAINT todo_foreign_key FOREIGN KEY (todo_id) REFERENCES todo(id);

CREATE TABLE todo (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    name VARCHAR(124) NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE NOT NULL,
    description VARCHAR(1024)
);

CREATE TABLE todo (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    name VARCHAR(124) NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE NOT NULL,
    description VARCHAR(1024)
);

insert into todo (id, name, created_time, description) values
    ('1', 'First todo', NOW(), 'The first todo to do'),
    ('2', 'Second todo', NOW(), 'The second todo to do');
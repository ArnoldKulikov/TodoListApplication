CREATE TABLE IF NOT EXISTS tasks
(
    id bigserial PRIMARY KEY ,
    closed boolean NOT NULL ,
    description varchar(255) NOT NULL
);
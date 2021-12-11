CREATE TABLE IF NOT EXISTS users
(
    id bigserial PRIMARY KEY ,
    login varchar(255) NOT NULL ,
    password varchar(255) NOT NULL
);
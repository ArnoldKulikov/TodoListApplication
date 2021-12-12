CREATE TABLE IF NOT EXISTS users
(
    id bigserial PRIMARY KEY ,
    password varchar(255) NOT NULL,
    user_name varchar(255) NOT NULL
);
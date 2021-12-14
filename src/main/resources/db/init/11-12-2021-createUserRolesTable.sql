CREATE TABLE IF NOT EXISTS users_roles
(
    users_id BIGINT REFERENCES users,
    roles_id BIGINT REFERENCES roles

);
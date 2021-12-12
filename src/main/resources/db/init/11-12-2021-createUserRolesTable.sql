CREATE TABLE IF NOT EXISTS users_roles
(
    id bigserial PRIMARY KEY,
    users_id BIGINT NOT NULL,
    roles_id BIGINT NOT NULL

);
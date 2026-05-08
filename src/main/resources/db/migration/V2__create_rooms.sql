CREATE TABLE rooms
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    created_by  UUID         REFERENCES users (id) ON DELETE SET NULL,
    created_at  TIMESTAMP    NOT NULL DEFAULT NOW(),

    CONSTRAINT unique_room_per_user UNIQUE (name, created_by)
);
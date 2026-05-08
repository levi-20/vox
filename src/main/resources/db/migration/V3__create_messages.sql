CREATE TABLE messages
(
    id        UUID     NOT NULL PRIMARY KEY,
    room_id   UUID      NOT NULL REFERENCES rooms (id) ON DELETE CASCADE,
    sender_id UUID      NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    message   TEXT      NOT NULL,
    sent_at   TIMESTAMP NOT NULL DEFAULT NOW()
);
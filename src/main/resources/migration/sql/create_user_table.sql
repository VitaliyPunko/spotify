Create SCHEMA IF NOT EXISTS spotify_auth;

CREATE TABLE spotify_auth.user(
                                  id varchar(255) PRIMARY KEY,
                                  username varchar(255) not null,
                                  password varchar(255) not null,
                                  role varchar(255) not null
);

INSERT INTO spotify_auth.user (id, username, password, role)
VALUES
    ('41aa0a8d-91af-4941-b1fe-dbd360a01b4c', 'test_user1', 'password', 'ROLE_USER');
INSERT INTO spotify_auth.user (id, username, password, role)
VALUES
    ('41aa0a8d-91af-4941-b1fe-dbd360a01b5c', 'test_user2', 'password', 'ROLE_USER');
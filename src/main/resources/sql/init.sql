CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(64) NOT NULL,
    author VARCHAR(128) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT true,
    user_id INT references users(user_id)
);
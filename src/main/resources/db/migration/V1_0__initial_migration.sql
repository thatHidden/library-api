CREATE TABLE IF NOT EXISTS books (
    id UUID PRIMARY KEY,
    title VARCHAR(50) UNIQUE NOT NULL,
    author VARCHAR(40) NOT NULL,
    published_date DATE NOT NULL
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO books (id, title, author, published_date) VALUES
    (UUID_GENERATE_V4(), 'Война и мир', 'Лев Толстой', '1869-01-01'),
    (UUID_GENERATE_V4(), 'Преступление и наказание', 'Фёдор Достоевский', '1866-01-01'),
    (UUID_GENERATE_V4(), 'Анна Каренина', 'Лев Толстой', '1877-01-01'),
    (UUID_GENERATE_V4(), 'Мастер и Маргарита', 'Михаил Булгаков', '1967-01-01'),
    (UUID_GENERATE_V4(), 'Отцы и дети', 'Иван Тургенев', '1862-01-01');

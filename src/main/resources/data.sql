DROP TABLE IF EXISTS movie;

CREATE TABLE movie (
    id INT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    year VARCHAR(250) NOT NULL,
    rating double NOT NULL
);
CREATE TABLE cars
(
    id    SERIAL PRIMARY KEY,
    make  VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year  INT         NOT NULL,
    price INT         NOT NULL
);

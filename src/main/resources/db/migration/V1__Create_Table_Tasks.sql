CREATE TABLE tasks (id INTEGER NOT NULL PRIMARY KEY,
description VARCHAR(255),
due_date Date,
status ENUM('PENDING', 'DONE'));